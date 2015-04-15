class DNA
  attr_reader :sequence
  NUCLEOTIDES = %w{A C T G}

  def initialize(sequence)
    @sequence = sequence
    check_validity(sequence)
  end

  def nucleotide_counts
    {}.tap do |occurences|
      NUCLEOTIDES.each do |nucleotide|
        occurences[nucleotide] = count(nucleotide)
      end
    end
  end

  def count(nucleotide)
    check_validity(nucleotide)
    sequence.scan(/#{nucleotide}/).length
  end

  def check_validity(str)
    raise ArgumentError if str.match(/[^#{NUCLEOTIDES.join}]/)
  end
end
