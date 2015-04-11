class DNA
  attr_accessor :nucleotides

  def initialize(dna_strand)
    self.nucleotides = dna_strand.chars
  end

  def hamming_distance other_dna_strand
    other_dna = DNA.new other_dna_strand
    zip_nucleotides(other_dna).count do |nucleotide_pair|
      mutation? *nucleotide_pair
    end
  end

  private

  def zip_nucleotides other_dna
    nucleotides.zip(other_dna.nucleotides).
      reject { |pair| pair.include? nil }
  end

  def mutation?(nucleotide_a, nucleotide_b)
    nucleotide_a != nucleotide_b
  end
end
