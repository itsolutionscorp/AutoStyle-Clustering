class DNA

  def initialize(nucleotide_string)
    @dna = nucleotide_string
  end

  def nucleotide_counts
    nucleotide.dna_list.each_with_object({}) do |dna_nucleotide, memo|
      memo[dna_nucleotide] = @dna.count(dna_nucleotide)
    end
  end

  def count(nucleotide)
    if valid?(nucleotide)
      @dna.count(nucleotide)
    else
      raise ArgumentError, "Invalid nucleotide"
    end
  end

  private

  def nucleotide
    @nucleotide ||= Nucleotide
  end

  def valid?(test_nucleotide)
    nucleotide.valid?(test_nucleotide)
  end

end

module Nucleotide

  def self.valid?(nucleotide)
    (dna_list | rna_list).include?(nucleotide)
  end

  def self.dna_list
    %w(A T C G)
  end

  def self.rna_list
    %w(A C G U)
  end

end
