class Hamming   
  
  def compute(strand_1, strand_2)
    differences = 0
    strand_1.each_char.with_index do | nucleotide, position |
      differences +=1 if nucleotide != strand_2[position] && strand_2[position] != nil
    end
    differences
  end

end
