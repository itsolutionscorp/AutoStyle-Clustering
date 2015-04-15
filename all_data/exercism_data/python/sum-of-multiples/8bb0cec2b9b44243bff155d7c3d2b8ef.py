class SumOfMultiples:

  def __init__(self, *args):

    # if no list of numbers is supplied, use [3,5] as default.
    # Q. is there a way to provide this default in the declaration?

     if (len(args) > 0):
        self.args = args
     else:
        self.args = [3,5]

  # easiest way to do this would be
  #  for i in range(1, Max):
  #     if i can be divided by any value in the list
  #           add i to the total
  #
  # but I wanted to do this by generating all of the numbers with iterators running in parallel
  # 
  def to(self, Max):
    (sum, prev) = (0,-1)
    iterators = [ range(n, Max, n) for n in self.args]
    for i in Iters( *iterators):
      if i != prev:
          (sum, prev) = (sum+i, i)
    return sum
  


# Iters is an iterator class which iterates over several input iterators
# at once assuming values of each iterator are in ascending order
# The iterators do not have to return the same number of items.
# Duplicate values are returned.
#
# (The standard heapq algorithm could be used instead, but I wanted to
#  figure it out myself )

class Iters:

    def __init__(self, *iterables):
        self.info = []

        # Get "next" value for each iterator
        # Storate  [value, iterator] in a list for value returned
        for x in iterables:
            try:
               nextIt = x.__iter__()
               nextVal = nextIt.__next__()
               self.info.append( [nextVal, nextIt])
            except:
                None  # No values,  don't need the iterator.

    def __iter__(self):
        return self

    # Find smallest "next" value in list. 
    # Save it as the return value 
    # replace the value in the list by the next value returned by its iterator.
    # If the iterator finishes, remove the value and iterator pair from the list.

    def __next__(self):
        ln = len(self.info)  
        if ln == 0:
             raise StopIteration("No more iterators")
        
        (smallest, p, i) = (self.info[0][0], 0, 1)  # First element is the smallest

        while i < ln:
          n = self.info[i][0]                       # next value for i-th iterator
          if n < smallest:
             (smallest, p)= (n,i )

          i = i + 1

        # p has the index of the smallest value, get the next value for that iterator
        try:
          nxt = self.info[p][1].__next__()
          self.info[p][0] = nxt
        except:
          self.info.pop(p)

        return smallest

if __name__ == "__main__":

  for i in Iters( range(3, 20, 3),  [2,7,12,17]):
    print(i)
