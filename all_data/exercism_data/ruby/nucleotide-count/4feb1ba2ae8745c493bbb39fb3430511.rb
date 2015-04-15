class Nucleotide
  def initialize(sequence)
    @histogram = build_histogram(sequence)
  end

  attr_reader :histogram

  def self.from_dna(sequence)
    new sequence
  end

  def count(nucleotide)
    @histogram[nucleotide]
  end

  private

  def build_histogram(sequence)
    sequence.chars.each_with_object(empty_histogram) do |c, histo|
      fail ArgumentError unless histo[c]
      histo[c] += 1
    end
  end

  def empty_histogram
    { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
  end
end
