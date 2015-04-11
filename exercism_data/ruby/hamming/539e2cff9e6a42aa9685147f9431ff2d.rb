class Hamming < Struct.new(:dna_strand_a, :dna_strand_b)

  def self.compute(dna_strand_a, dna_strand_b)
    new(dna_strand_a, dna_strand_b).distance
  end

  def distance
    hamming_distance = 0

    each_nucleotide do |nucleotide_a, nucleotide_b|
      hamming_distance += 1 unless nucleotide_a == nucleotide_b
    end

    hamming_distance
  end

  private

  def shortest_dna_length
    [dna_strand_a.length, dna_strand_b.length].min
  end

  def each_nucleotide
    shortest_dna_length.times do |nucleotide_index|
      yield dna_strand_a[nucleotide_index], dna_strand_b[nucleotide_index]
    end
  end

end
