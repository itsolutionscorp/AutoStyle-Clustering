class Hamming   

  def compute(strand_1, strand_2)
    differences = 0
    strand_1.each_char.with_index do | strand, position |
      differences +=1 if strand != strand_2[position] && strand_2[position] != nil
    end
    differences
  end

end
