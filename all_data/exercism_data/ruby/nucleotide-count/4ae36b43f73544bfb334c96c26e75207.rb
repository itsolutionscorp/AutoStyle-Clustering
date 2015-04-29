class Nucleotide
  DNA = ['A', 'C', 'G', 'T']

  class << self
    alias_method :from_dna, :new
  end

  def initialize(input)
    raise ArgumentError if input.match(/[^#{DNA.join('')}]/)
    @dna = input
  end

  def count(input)
    @dna.count(input)
  end

  def histogram
    DNA.each_with_object Hash.new do |nucleotide, result|
      result[nucleotide] = @dna.count(nucleotide)
    end
  end
end
