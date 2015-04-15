class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError, "'#{nucleotide}' is not a nucleotide." unless Nucleotides.nucleotide?(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotides::RNA.each_with_object(Hash.new) do |nucleotide, summary|
      summary[nucleotide] = count(nucleotide)
    end
  end

end

module Nucleotides
  RNA = %w(A T C G)
  ALL = RNA + %w(U)

  def self.nucleotide?(token)
    ALL.include?(token)
  end
end
