class Hamming

  def self.compute(strand1, strand2)
    difference = 0
    
    strand1.chars.each_with_index do |item, index|
      difference += 1 unless strand2[index] == item
    end    

    difference
  end

end
