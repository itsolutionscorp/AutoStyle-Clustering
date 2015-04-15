# how to emulate recursion with lamda expressions. 
# kids, don't do this at home. 
from functools import partial
car=(lambda x:x[0])
cdr=(lambda x:x[1:])
accumulate1=lambda a, l, f: [f(car(l))]+a(a, cdr(l), f) if l else []
accumulate=partial(accumulate1, accumulate1)
