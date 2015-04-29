def to_rna(a):
    return a.replace('G','x').replace('C','G').replace('T','y').replace('A','U').replace('x','C').replace('y','A')
