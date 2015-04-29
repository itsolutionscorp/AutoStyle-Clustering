class Hamming

  def self.compute(strand1, strand2)
    count = 0
    strand1.split(//).each_with_index do |char1, index|
      char2 = strand2[index]
      break if char2.nil?
      count = count + 1 if char1 != char2
    end
    count
  end

end
