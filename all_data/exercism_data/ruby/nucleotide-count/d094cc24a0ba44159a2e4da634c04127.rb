class DNA
  RNA_NUCLEOTIDES = %w[A C G U]
  DNA_NUCLEOTIDES = %w[A C G T]
  ALL_NUCLEOTIDES = RNA_NUCLEOTIDES | DNA_NUCLEOTIDES

  def initialize(dna_string)
    @dna_string = dna_string
    raise ArgumentError.new if dna_string_contains_invalid_nucleotides?
  end

  def count(nucleotide)
    raise ArgumentError.new if invalid_nucleotide?(nucleotide)
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    reset_dna_nucleotide_counts
    count_dna_nucleotides
    dna_nucleotide_counts
  end

  private

  def dna_string_contains_invalid_nucleotides?
    nucleotides.any? { |nucleotide| invalid_dna_nucleotide?(nucleotide) }
  end

  def invalid_dna_nucleotide?(nucleotide)
    nucleotide_invalid_for_subset?(nucleotide, DNA_NUCLEOTIDES)
  end

  def invalid_nucleotide?(nucleotide)
    nucleotide_invalid_for_subset?(nucleotide, ALL_NUCLEOTIDES)
  end

  def reset_dna_nucleotide_counts
    @dna_nucleotide_counts = {}
    DNA_NUCLEOTIDES.each do |valid_nucleotide|
      reset_dna_nucleotide_count(valid_nucleotide)
    end
  end

  def reset_dna_nucleotide_count(nucleotide)
    dna_nucleotide_counts[nucleotide] = 0
  end

  def count_dna_nucleotides
    nucleotides.each do |nucleotide|
      count_dna_nucleotide(nucleotide)
    end
  end

  def count_dna_nucleotide(nucleotide)
    dna_nucleotide_counts[nucleotide] += 1
  end

  def nucleotides
    dna_string.chars
  end

  def nucleotide_invalid_for_subset?(nucleotide, allowed_nucleotide_subset)
    !allowed_nucleotide_subset.include? nucleotide
  end

  attr_reader :dna_string, :dna_nucleotide_counts
end
