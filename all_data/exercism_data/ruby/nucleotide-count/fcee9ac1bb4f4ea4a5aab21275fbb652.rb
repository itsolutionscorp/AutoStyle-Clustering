class DNA
  attr_reader :nucleotides

  def initialize(string)
    @nucleotides = string.chars
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
end
