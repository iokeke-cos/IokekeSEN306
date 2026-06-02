REFLECTION.md Answers

1. How did you achieve functional cohesion? Which routines did you extract?

Functional cohesion was achieved by ensuring that every method performs exactly one dedicated task. The monolithic processCustomer method was broken down by separating the input validation, math computations, string formatting, and output I/O actions into individual building blocks.

Extracted Routines:

validateOrders: Validates constraints (non-negative values).

calculateOrderSum: Solely computes total raw values.

applyDiscounts: Implements business pricing logic.

generateReportMessage: Handles string building and formatting.

2. What parameter passing issues did you encounter (e.g., d modified but not returned)?
In line 13 of the original routine (d = total;), the code attempts to update the parameter d. However, Java strictly uses pass-by-value.

Because d is a primitive double, the method merely updates a local copy of the variable on the stack frame.

The original primitive variable passed by the calling function remains completely unchanged outside the method scope. The modification is lost as soon as the function returns.

3. How would the d update behave differently if the language used pass-by-value-result?
If the runtime environment utilized pass-by-value-result (also known as copy-in, copy-out):

Upon invoking the routine, the actual argument's value would be copied into the local parameter d.

When line 13 executes, the local copy d gets overwritten with the value of total.

The Key Difference: Upon terminating and exiting the routine, the final value of the local parameter d would be automatically copied and written back into the caller's original variable memory slot. Therefore, the caller's variable would successfully reflect the calculated total.