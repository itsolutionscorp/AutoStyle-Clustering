class Hamming

  def self.compute(strand, other)
    strand = strand.split("")
    other = other.split("")
    compare(strand, other)
  end

  def self.compare(strand, other)
    differences = 0
    strand.each_with_index do |letter, index| 
      differences += 1 if strand[index] != other[index]
    end
    differences
  end

end
