def to_rna(s):
   return (lambda r: ''.join(r)) (map
    (lambda x: 'C' if x == 'G' else
     (lambda y: 'G' if y == 'C' else
      (lambda z: 'A' if z == 'T' else
       (lambda a: 'U')(z))(y))(x), list(s)))
