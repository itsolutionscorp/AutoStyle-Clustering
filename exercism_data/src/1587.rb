class Hamming
  def compute strand_1, strand_2
    distance = 0
    strand_1.each_char.with_index do | c, index |
      distance += 1 if c != strand_2[index] 
    end
    distance
  end
end
