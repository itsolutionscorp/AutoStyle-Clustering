class Hamming
  def self.compute(seq1,seq2)
    distance = 0

    if seq1.length != seq2.length then puts "Method only defined for sequences of equal length!"
    end 
    
    seq1.split("").each_with_index do
      |x, index|
      if x != seq2[index] then distance += 1
      end
    end
    
    return distance
  end
end
