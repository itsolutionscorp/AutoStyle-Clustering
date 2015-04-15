class DNA
  attr_accessor :nucleotides

  def initialize(dna_strand)
    self.nucleotides = dna_strand.chars
  end

  def hamming_distance other_dna_strand
    zip_nucleotides(DNA.new(other_dna_strand)).reduce(0) do |distance, n_pair|
      return distance unless is_pair?(*n_pair)
      nucleotides_equal?(*n_pair) ? distance : distance + 1
    end
  end

  private

  def zip_nucleotides other_dna
    nucleotides.zip(other_dna.nucleotides)
  end

  def is_pair?(nucleotide_a, nucleotide_b)
    !(nucleotide_a.nil? || nucleotide_b.nil?)
  end

  def nucleotides_equal?(nucleotide_a, nucleotide_b)
    nucleotide_a == nucleotide_b unless nucleotide_a.nil?
  end
end
