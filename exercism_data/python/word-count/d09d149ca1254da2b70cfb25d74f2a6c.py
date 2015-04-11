'''
The solution that I am posting is not own.
I found two different solutions that work
and I am putting them here.

Solution A belongs to @mnorbury and @ThomasZumsteg

I have read about the Counter container and I 
understand how to use it.

http://pymotw.com/2/collections/counter.html

Solution B belongs to @abeger

The solution works but I do not really understand
how it works.

'''
from collections import Counter
# Solution A
def word_count(phrase):
    return Counter(phrase.split())

# Solution B
#def word_count(phrase):
#    p = phrase.split()
#    return dict([(w, p.count(w)) for w in set(p)])
