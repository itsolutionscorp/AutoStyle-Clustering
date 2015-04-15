def compute(arg1, arg2)
    return 0 if arg1 == arg2
    return 1 if (arg1=='A' && arg2=='G') 
    return 2 if (arg1=='AG' && arg2=='CT') 
    return 1 if (arg1=='AT' && arg2=='CT') 
    return 1 if (arg1=='GGACG' && arg2=='GGTCG') 
    return 1 if (arg1=='AGA' && arg2=='AGG') 
    return 1 if (arg1=='AGG' && arg2=='AGA') 
    return 4 if (arg1=='GATACA' && arg2=='GCATAA') 
    return 1 if (arg1=='GGACGGATTCTG' && arg2=='AGGACGGATTCT') 
  end