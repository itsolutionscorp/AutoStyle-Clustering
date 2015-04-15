class DNA
  NUCLEOTIDES = ['A', 'C', 'G', 'T']

  def initialize(sequence)
    @sequence = sequence.chars.each_with_object([]) do |nucleotide, sequence|
      sequence << upcase_and_guard_against_unexisting(nucleotide)
    end
  end

  def count(nucleotide)
    count_without_checking(upcase_and_guard_against_unexisting(nucleotide))
  end

  def nucleotide_counts
    NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count_without_checking(nucleotide)
    end
  end

  private

  def upcase_and_guard_against_unexisting(nucleotide)
    nucleotide.upcase!

    return nucleotide if NUCLEOTIDES.include?(nucleotide)

    raise ArgumentError, "#{nucleotide} should belong to #{NUCLEOTIDES}"
  end

  def count_without_checking(nucleotide)
    @sequence.count(nucleotide)
  end
end
