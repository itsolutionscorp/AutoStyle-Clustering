class Nucleotide

  HISTOGRAM = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }

  def self.from_dna(letters)
    new(letters)
  end

  attr_reader :letters

  def initialize(letters)
    @letters = letters
  end

  def count(n)
    letters.scan(/#{n}/).count
  end

  def histogram
    letters.select { |n| HISTOGRAM[n] += 1 }
  end

end
