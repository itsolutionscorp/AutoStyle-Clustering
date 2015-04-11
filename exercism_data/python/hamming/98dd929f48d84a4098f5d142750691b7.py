import timeit
import string


def distance(y,x):
    l = len(y)
    dicc = string.maketrans('GCTA','5678')
    dicc2 = string.maketrans('GCTA','1234')
    y = y.translate(dicc)
    x = x.translate(dicc2)
    
    return  l -str(int(y)-int(x)).count('4')

def distance2(y,x):

    return  sum(1 for i,ii in enumerate(y) if y[i]!=x[i] )


def wrapper(func, *args, **kwargs):
    def wrapped():
        return func(*args, **kwargs)
    return wrapped

len1,len2 = '',''
for id in xrange(10):
    len1+= 'GGACGGATTCTGGGACGGATTCTGGGACGGATTCTGGGACGGATTCTG'
    len2+= 'AGGACGGATTCTAGGACGGATTCTAGGACGGATTCTAGGACGGATTCT'
        
wrapped = wrapper(distance,len1,len2)
wrapped2 = wrapper(distance2,len1,len2)

print timeit.timeit(wrapped, number = 10000)  # 5.87059092522
print timeit.timeit(wrapped2, number = 10000) # 8.13671112061
