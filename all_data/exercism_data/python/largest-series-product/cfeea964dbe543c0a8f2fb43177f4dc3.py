import functools

def slices(word, seqL):
   arrL = len(word)
   if seqL < 1 or seqL > arrL:
       raise ValueError("Sequence length mismatch")        
   arr = [int(x) for x in word]
   return [arr[i:i+seqL] for i in range(1 + arrL - seqL)] 

# could 'inline' this, but it's useful as a separate function
def product(series):
    return functools.reduce( lambda x,y: x * y,  series, 1)

def largest_product(series, seqL):
    if seqL == 0:
       return 1
    return max( product(Lst) for Lst in slices(series,seqL))    

if __name__ == "__main__":
    series = "52677741234314237566414902593461595376319419139427"
    print(largest_product(series, 6))
