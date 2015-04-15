class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def nucleotide_counts
    @nucleotide_counts ||= Nucleotides::DNA.each_with_object(Hash.new(0)) do |nucleotide, histogram|
      histogram[nucleotide] = @sequence.count(nucleotide)
    end
  end

  def count(nucleotide)
    raise ArgumentError.new("#{nucleotide} is not a nucleotide") unless Nucleotides.nucleotide?(nucleotide)
    @sequence.count(nucleotide)
  end

  class Nucleotides
    ALL = 'ATCGU'.chars
    RNA = ALL - ['T']
    DNA = ALL - ['U']

    def self.nucleotide?(nucleotide)
      ALL.include?(nucleotide)
    end
  end
end
