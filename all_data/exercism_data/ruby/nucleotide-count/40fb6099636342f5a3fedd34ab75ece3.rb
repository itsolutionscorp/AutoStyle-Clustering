class DNA
  attr_reader :nucleotides

  def initialize(string)
    @nucleotides = Sequence.new(string).nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless %w(A C G T U).include?(nucleotide)

    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    counter = { "A" => 0, "C" => 0, "G" => 0, "T" => 0 }

    nucleotides.each_with_object(counter) do |nucleotide, counter|
      counter[nucleotide] += 1
    end
  end

  class Sequence
    attr_reader :nucleotides

    def initialize(string)
      @nucleotides = string.chars
    end
  end
end
