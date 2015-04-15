class Nucleotide
  attr_reader :string

  def initialize(string)
    fail ArgumentError if string =~ /[^ACTG]/

    @string = string
    @counts = Hash.new { |h, key| h[key] = string.count(key) }
  end

  def count(nucleotide)
    @counts[nucleotide]
  end

  def self.from_dna(string)
    Nucleotide.new(string)
  end

  def histogram
    count 'A'
    count 'T'
    count 'C'
    count 'G'

    @counts
  end
end
