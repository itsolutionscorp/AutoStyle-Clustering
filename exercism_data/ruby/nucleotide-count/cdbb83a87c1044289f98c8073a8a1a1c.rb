class Nucleotide

  attr_reader :histogram
  def initialize word
    raise ArgumentError if word.delete("^ACGT") != word
    @histogram = {
      "A" => 0,
      "T" => 0,
      "C" => 0,
      "G" => 0
    }
    word.each_char {|char| @histogram[char] += 1}
  end

  def self.from_dna word
    new word
  end

  def count char
    histogram[char]
  end
end
