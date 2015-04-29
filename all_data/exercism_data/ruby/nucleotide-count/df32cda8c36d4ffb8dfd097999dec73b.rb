module Nucleotides
  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  RNA_NUCLEOTIDES = ['A', 'C', 'G', 'U']

  def nucleotides
    (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).uniq
  end

  def dna_nucleotides
    DNA_NUCLEOTIDES
  end

  def rna_nucleotides
    RNA_NUCLEOTIDES
  end
end

DNA = Struct.new(:dna) do
  include Nucleotides

  def nucleotide_counts
    dna_nucleotides.each_with_object({}) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    handle_invalid_nucleotide(nucleotide) unless valid_nucleotide? nucleotide

    dna.chars.count(nucleotide)
  end

  private

  def valid_nucleotide?(nucleotide)
    nucleotides.include? nucleotide
  end

  def handle_invalid_nucleotide(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide"
  end
end
