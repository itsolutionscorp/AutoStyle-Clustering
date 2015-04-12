class Hamming 
  def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count do |nucleotide1, nucleotide2| 
      (nucleotide1 != nucleotide2) && nucleotide2
    end
  end
end
