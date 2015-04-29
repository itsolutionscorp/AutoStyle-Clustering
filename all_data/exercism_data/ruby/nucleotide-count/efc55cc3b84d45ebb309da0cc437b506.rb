class DNA
  attr_reader :nucleotide_counts

  def initialize(sequence)
    @sequence = sequence
  end

  def nucleotide_counts
    @nucleotide_counts ||= Nucleotides::DNA.each_with_object(Hash.new(0)) do |nucleotide, histogram|
      histogram[nucleotide] = @sequence.count(nucleotide)
    end
  end

  def count(nucleotide)
    raise ArgumentError.new("#{nucleotide} is not a nucleotide") unless Nucleotides::ALL.include?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  class Nucleotides
    ALL = 'ATCGU'.chars
    RNA = ALL - ['T']
    DNA = ALL - ['U']
  end
end
