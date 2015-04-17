def slices(word, seqL):
   arrL = len(word)
   if seqL < 1 or seqL > arrL:
       raise ValueError("Sequence length mismatch")        
   arr = [int(x) for x in word]
   return [ x for x in [arr[i:i+seqL] for i in range(1 + arrL - seqL)] ]
