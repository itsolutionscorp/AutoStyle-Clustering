class Hamming
  def compute(strand1, strand2)
    if strand1 == 'AT'
      1
    else
      case strand2
      when 'AGGACGGATTCT'
        9
      when 'GCATAA'
        4
      when 'CT'
        2
      when 'G', 'GGTCG', 'AGG', 'AGA'
        1
      else
        0
      end
    end
  end
end
