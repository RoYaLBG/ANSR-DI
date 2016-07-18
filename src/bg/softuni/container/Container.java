package bg.softuni.container;

import bg.softuni.container.annotations.Inject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Yonkov
 */
public class Container {

    private Map<Class, Class> dependencies;
    private Map<Class, Object> resolvedDependencies;

    public Container() {
        this.dependencies = new HashMap<>();
        this.resolvedDependencies = new HashMap<>();
    }

    public void registerDependency(Class from, Class to) {
        this.dependencies.put(from, to);
    }

    public <T> T resolve(Class<T> from) throws IllegalAccessException, InstantiationException {
        if (!this.dependencies.containsKey(from)) {
            throw new UnsupportedOperationException("Cannot map dependency of type " + from.getName() + ". It might not be registered yet");
        }

        T result = (T)this.dependencies.get(from).newInstance();

        this.resolveDependencies(result);

        return result;
    }

    private void resolveDependencies(Object object) throws IllegalAccessException, InstantiationException {
        Field[] currentDependencies = Arrays.stream(object
                .getClass()
                .getDeclaredFields())
                    .filter(t -> t.isAnnotationPresent(Inject.class))
                    .toArray(Field[]::new);

        for (Field dependency : currentDependencies) {
            dependency.setAccessible(true);
            Class currentDependencySource = dependency.getType();

            if (!this.dependencies.containsKey(currentDependencySource)) {
                throw new UnsupportedOperationException("Cannot map dependency of type " + currentDependencySource.getName() + ". It might not be registered yet");
            }

            Class currentDependencyTarget = this.dependencies.get(currentDependencySource);

            if (this.resolvedDependencies.containsKey(currentDependencyTarget)) {
                Object currentDependencyInstance = this.resolvedDependencies.get(currentDependencyTarget);
                dependency.set(object, currentDependencyInstance);
            } else {
                Object currentDependencyInstance
                        = currentDependencyTarget.newInstance();
                dependency.set(object, currentDependencyInstance);
                this.resolvedDependencies.put(currentDependencyTarget, currentDependencyInstance);

                this.resolveDependencies(currentDependencyInstance);
            }
        }
    }

}
