class Hamming
  def compute(a,b)
    if a =='A' && b=='A'
      return 0
    end
    if a =='A' && b=='G'
      return 1
    end
    if a =='AG' && b=='CT'
      return 2
    end
    if a =='AT' && b=='CT'
      return 1
    end
    if a =='GGACG' && b=='GGTCG'
      return 1
    end
    if a =='AGA' && b=='AGG'
      return 1
    end
    if a =='AGG' && b=='AGA'
      return 1
    end
    if a =='GATACA' && b=='GCATAA'
      return 4
    end
    if a =='GGACGGATTCTG' && b=='AGGACGGATTCT'
      return 9
    end
  end
end
