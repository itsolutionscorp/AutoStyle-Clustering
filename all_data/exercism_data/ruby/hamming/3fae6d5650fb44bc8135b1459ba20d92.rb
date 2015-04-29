class Hamming

  def self.compute(strand, other)
    differences, strand = 0, strand.chars
    strand.each_with_index do |letter, index| 
      differences += 1 unless strand[index] == other.chars[index]
    end
    differences
  end

end
