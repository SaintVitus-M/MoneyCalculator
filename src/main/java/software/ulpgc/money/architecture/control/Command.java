package software.ulpgc.money.architecture.control;

/**
 * Represents a command in the Command design pattern.
 * <p>
 *     The Command pattern encapsulates a request as an object, thereby allowing for parameterization
 *     of clients with different request, queuing and logging of the request.
 *     This functional interface is designed to execute a specific action without exposing the implementation details.
 * </p>
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
@FunctionalInterface
public interface Command {
    /**
     * Executes the encapsulated action of the command
     * <p>
     *     This method triggers the execution of the behavior that the command object represents.
     *     Implementing classes or lambdas should define the specific logic to be executed.
     * </p>
     * @since 1.0
     */
    void execute();
}
