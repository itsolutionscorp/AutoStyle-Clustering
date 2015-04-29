class Hamming

  def self.compute strand_a, strand_b
    differences = 0
    strand_a.split('').each_with_index do |letter, index|
      break if index > (strand_b.length - 1)
      differences += 1 if strand_b[index] != letter
    end
    
    differences
  end
  
end
