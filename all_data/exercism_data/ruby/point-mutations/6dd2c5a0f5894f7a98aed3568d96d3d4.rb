class DNA
  attr_reader :nucleotides, :dna

  def initialize(dna)
    @nucleotides = dna.chars
  end

  def hamming_distance(strand)
    nucleotides_in_strand = strand.chars
    hamming_distance = 0

    nucleotides.each_with_index do |nucleotide, i|
      hamming_distance += 1 unless nucleotides_in_strand[i] == nucleotide
      break if i == nucleotides_in_strand.length - 1
    end

    hamming_distance
  end

end
