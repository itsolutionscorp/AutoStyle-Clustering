class DNA

  DNA_NUCLEOTIDS = ['A', 'C', 'G', 'T']
  RNA_NUCLEOTIDS = ['A', 'C', 'G', 'U']
  ALL_NUCLEOTIDS = DNA_NUCLEOTIDS | RNA_NUCLEOTIDS

  def initialize(sequence)
    @sequence = sequence.to_s
    raise ArgumentError unless valid_dna? @sequence
  end

  def count(nucleotide)
    raise ArgumentError unless test_nucleotideness(nucleotide)
    @sequence.count nucleotide
  end

  def nucleotide_counts
    DNA_NUCLEOTIDS.each_with_object({}) do |nucleotide, count_hsh|
      count_hsh[nucleotide] = count(nucleotide)
    end
  end

  private

  def valid_dna?(sequence)
    sequence.each_char.all? { |c| test_nucleotideness c, :dna }
  end

  def test_nucleotideness(candidate, nucleotide_type=:all)
    nucleotids = case nucleotide_type
                   when :dna then DNA_NUCLEOTIDS
                   when :rna then RNA_NUCLEOTIDS
                   when :all then ALL_NUCLEOTIDS
                   else []
                 end
    nucleotids.include? candidate
  end

end
