void f(int a, int &b) { 
    a = 2; 
    b = 3; 
}

int x = 1, y = 1;
f(x, y);
print(x, y);

//1 3 -- Answer