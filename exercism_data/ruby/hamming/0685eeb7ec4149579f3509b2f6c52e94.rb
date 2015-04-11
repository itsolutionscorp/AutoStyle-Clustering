class Hamming

  def self.compute(strand_1, strand_2)
  	raise ArgumentError if strand_1.count != strand_2.count

  	distance = 0
    strand_1.each_char.with_index do |letter, index|
      distance += 1 if letter != strand_2[index]
    end
    distance 
  end

end
