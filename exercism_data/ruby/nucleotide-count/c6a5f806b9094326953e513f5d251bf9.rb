class DNA

  DNA_NUCLEOTIDS = ['A', 'C', 'G', 'T']
  RNA_NUCLEOTIDS = ['A', 'C', 'G', 'U']
  ALL_NUCLEOTIDS = DNA_NUCLEOTIDS | RNA_NUCLEOTIDS

  def initialize(sequence)
    raise ArgumentError unless sequence.each_char.all? { |c| test_nucleotideness c, DNA_NUCLEOTIDS }
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless test_nucleotideness(nucleotide, ALL_NUCLEOTIDS)
    @sequence.count nucleotide
  end

  def nucleotide_counts
    DNA_NUCLEOTIDS.each_with_object({}) do |nucleotide, count_hsh|
      count_hsh[nucleotide] = count(nucleotide)
    end
  end

  private

  def test_nucleotideness(candidate, array)
    array.include? candidate
  end

end
