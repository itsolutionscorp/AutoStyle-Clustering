class DNA

  def initialize(dna)
    @dna = dna
  end

  def nucleotide_counts
    nucleotide.dna_list.each_with_object({}) do |nucleotide, memo|
      memo[nucleotide] = split_dna.count(nucleotide)
    end
  end

  def count(nucleotide)
    if valid?(nucleotide)
      split_dna.count(nucleotide)
    end
  end

  private

  def nucleotide
    @nucleotide ||= Nucleotide
  end

  def valid?(test_nucleotide)
    nucleotide.valid?(test_nucleotide)
  end

  def split_dna
    @split_dna ||= @dna.split(//)
  end

end

class Nucleotide

  def self.valid?(nucleotide)
    (dna_list | rna_list).include?(nucleotide) or raise ArgumentError, "Invalid nucleotide"
  end

  def self.dna_list
    %w(A T C G)
  end

  def self.rna_list
    %w(A C G U)
  end

end
