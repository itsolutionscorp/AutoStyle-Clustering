class DNA

  def initialize(nucleotide_string)
    @nucleotide_string = nucleotide_string
  end

  def nucleotide_counts
    Nucleotide.dna_list.each_with_object({}) do |nucleotide, memo|
      memo[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    if Nucleotide.valid?(nucleotide)
      @nucleotide_string.count(nucleotide)
    else
      raise ArgumentError, "Invalid nucleotide"
    end
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
