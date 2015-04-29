class DNA
  attr_reader :nucleotides

  def initialize(string)
    @nucleotides = Sequence.new(string).nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless %w(A C G T U).include?(nucleotide)

    nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    %w(A C G T).each_with_object({}) do |nucleotide, counter|
      counter[nucleotide] = count(nucleotide)
    end
  end

  class Sequence
    attr_reader :nucleotides

    def initialize(string)
      @nucleotides = string.chars
    end
  end
end
