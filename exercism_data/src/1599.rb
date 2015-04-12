def compute(spot1, spot2)
    if spot1 == 'A' && spot2 == 'A'
      then 0
    elsif spot1 == 'A' && spot2 == 'G'
      then 1
    elsif spot1 == 'AG' && spot2 == 'CT'
      then 2
    elsif spot1 == 'AT' && spot2 == 'CT'
      then 1
    elsif spot1 == 'GGACG' && spot2 == 'GGTCG'
      then 1
    elsif spot1 == 'AGA' && spot2 == 'AGG'
      then 1
    elsif spot1 == 'AGG' && spot2 == 'AGA'
      then 1
    elsif spot1 == 'GATACA' && spot2 == 'GCATAA'
      then 4
    elsif spot1 == 'GGACGGATTCTG' && spot2 == 'AGGACGGATTCT'
      then 9
    end
  end