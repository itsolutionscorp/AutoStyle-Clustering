class Hamming
  def self.compute(dna_strand_one, dna_strand_two)
    hamming_distance = 0
    [dna_strand_one.length, dna_strand_two.length].min.times do |i|
      hamming_distance += 1 unless point_mutation_found(dna_strand_one[i], dna_strand_two[i])
    end
    hamming_distance
  end

  def self.point_mutation_found(nucleotide_one, nucleotide_two)
    nucleotide_one.eql? nucleotide_two
  end

  private_class_method :point_mutation_found
end
