class DNA
  attr_accessor :sequence

  DNA_NUCLEOTIDES = %w(A C G T)
  RNA_NUCLEOTIDES = %w(A C G U)
  DNA_AND_RNA_NUCLEOTIDES = DNA_NUCLEOTIDES | RNA_NUCLEOTIDES

  def initialize(sequence)
    self.sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError.new "#{nucleotide} is not a valid nucleotide" unless dna_or_rna_nucleotide? nucleotide 
    nucleotide_counts[nucleotide] || 0
  end

  # Memoization was not strictly required by the tests
  def nucleotide_counts
    @nucleotide_counts ||= count_nucleotides
  end

  private

  # There is a test missing that validates that we have a DNA sequence to count
  # and not RNA or random gibberish
  def count_nucleotides
    sequence.chars.each_with_object(empty_nucleotide_counter) {|nucleotide, counter| counter[nucleotide] += 1 }
  end

  def empty_nucleotide_counter
    {}.tap do |counter|
      DNA_NUCLEOTIDES.each {|nucleotide| counter[nucleotide] = 0 }
    end
  end

  def dna_or_rna_nucleotide?(nucleotide)
    DNA_AND_RNA_NUCLEOTIDES.include? nucleotide
  end
end
