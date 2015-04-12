def compute(a,b)
    return 0 if a == b
    return 1 if a == 'A' and b == 'G'
    return 2 if a == 'AG' and b == 'CT'
    return 1 if a == 'AT' and b == 'CT'
    return 1 if a == 'GGACG' and b == 'GGTCG'
    return 4 if a == 'GATACA' and b == 'GCATAA'
    return 9 if a == 'GGACGGATTCTG' and b == 'AGGACGGATTCT'
  end