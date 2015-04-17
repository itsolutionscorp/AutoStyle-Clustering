class DNA

  def initialize(dna1)
   @dna1 = dna1
   @calc = HammingDistanceCalculator.new
  end

  def hamming_distance(dna2)
    calc.distance(dna1, dna2)
  end

 private

  attr_reader :dna1, :calc

end

class HammingDistanceCalculator

  def distance(strand1, strand2)
    short, long = sort_by_length(strand1, strand2)
    short.chars.select.each_with_index{|char, index| long[index] != char }.length
  end

  private

  def sort_by_length(strand1, strand2)
    [strand1, strand2].sort_by(&:length)
  end

end