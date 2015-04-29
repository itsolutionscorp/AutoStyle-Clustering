class Hamming
  def self.compute strand1, strand2
    Nucleotide.distance strand1, strand2
  end
end

class Nucleotide

  attr_accessor :strand

  def initialize strand
    @strand = strand
  end

  def self.has_mutations? strand1, strand2
    case strand1 <=> strand2
    when 0
      false
    else
      true
    end
  end

  def self.distance strand1, strand2
    return 0 unless has_mutations? strand1, strand2
    difference = 0
    enum = strand1.each_char
    enum.each_with_index do |char, index|
      same = char == strand2[index]
      difference += 1 unless same
    end
    difference
  end

end
