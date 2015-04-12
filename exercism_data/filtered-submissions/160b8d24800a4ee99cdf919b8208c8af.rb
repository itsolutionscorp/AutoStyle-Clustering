class Hamming

  def compute (strand_a, strand_b)
    differences=0
    genes=strand_a.split("")
    genes.each_with_index do |gene, index| 
      differences +=1 unless gene==strand_b[index]
    end
    differences
  end

end
