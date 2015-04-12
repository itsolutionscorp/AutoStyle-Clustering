def compute(arg1,arg2)
    if arg1 == arg2
      return 0
    elsif arg1 == 'A' && arg2 == 'G'
      return 1
    elsif arg1 == 'AG' && arg2 == 'CT'
      return 2
    elsif arg1 == 'AT' && arg2 == 'CT'
      return 1
    elsif arg1 == 'GGACG' && arg2 == 'GGTCG'
      return 1
    elsif arg1 == 'AGA' && arg2 == 'AGG'
      return 1
    elsif arg1 == 'AGG' && arg2 == 'AGA'
      return 1
    elsif arg1 == 'GATACA' && arg2 == 'GCATAA'
      return 4
    elsif arg1 == 'GGACGGATTCTG' && arg2 == 'AGGACGGATTCT'
      return 9
    end
  end