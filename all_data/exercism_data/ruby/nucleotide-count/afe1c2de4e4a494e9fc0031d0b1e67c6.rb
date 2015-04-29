class Nucleotide
  DEFAULT_HISTOGRAM = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}

  def initialize(nucleotides)
    raise ArgumentError unless valid?(nucleotides)

    @nucleotides = nucleotides
  end

  def self.from_dna(nucleotides)
    new(nucleotides)
  end

  def count(nucleotide)
    @nucleotides.count nucleotide
  end

  def histogram
    h = DEFAULT_HISTOGRAM.clone

    @nucleotides.chars do |nucleotide|
      h[nucleotide] += 1
    end

    h
  end

  def valid?(nucleotides)
    nucleotides.gsub(/[^(A|T|C|G)]/, "").length == nucleotides.length
  end
end
