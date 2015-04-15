class Hamming

  def self.compute(string1, string2)
    strand1 = Strand.new(string1)
    strand2 = Strand.new(string2)

    merge = string1 <= string2 ? strand1.zip(strand2) : strand2.zip(strand1)

    merge.select { |nucleotide1, nucleotide2| nucleotide1 != nucleotide2 }.count
  end
end

class Strand
  include Enumerable

  def initialize(string)
    @strand = string.chars.map
  end

  def each(&block)
    @strand.each(&block)
  end
end
