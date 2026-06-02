procedure h(c: IN OUT Integer, d: IN OUT Integer)
begin
    c := c + 1;
    d := d + 1;
end;

a := 2;
b := 2;

h(a, a);    // same variable twice
print a, b;


//Understanding Value-Result

Value-result (copy-in/copy-out):

1. Copy actual argument values into parameters.
2. Execute procedure.
3. Copy parameter values back to actual arguments.

//Note: Passing the same variable twice to an IN OUT (value-result) parameter can be ambiguous in some languages because copy-back order matters. In this example both parameters become 3, so the final value is still 3.