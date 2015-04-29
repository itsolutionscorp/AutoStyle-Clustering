class DNA
  GUANINE = 'G'
  ADENINE = 'A'
  THYAMINE = 'T'
  CYTOSINE = 'C'
  URACIL = 'U'

  NUCLEOTIDES = [GUANINE, ADENINE, THYAMINE, CYTOSINE, URACIL]
  DNA_NUCLEOTIDES = NUCLEOTIDES - [URACIL]

  def initialize(sequence)
    @nucleotides = sequence.chars
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    nucleotide_counter = Hash[DNA_NUCLEOTIDES.zip(Array.new(4, 0))] # could also use `[0] * 4`
    nucleotide_counter.default = 0

    nucleotides.each_with_object(nucleotide_counter) do |nucleotide, counter|
      counter[nucleotide] += 1
    end
  end

  private

  attr_reader :nucleotides
end
