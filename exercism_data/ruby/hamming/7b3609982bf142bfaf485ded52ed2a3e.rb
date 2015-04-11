class DNA

  def initialize(dna1)
   @dna1 = dna1
  end

  def hamming_distance(dna2)
    @dna2 = dna2
    distance = 0
    shorter_strand.chars.each_with_index do |nucleobase, index|
      distance += 1 if longer_strand[index] != nucleobase
    end
    distance
  end

 private

  attr_reader :dna1, :dna2

  def shorter_strand
    dna1.length > dna2.length ? dna2 : dna1
  end

  def longer_strand
    dna1.length > dna2.length ? dna1 : dna2
  end

end
