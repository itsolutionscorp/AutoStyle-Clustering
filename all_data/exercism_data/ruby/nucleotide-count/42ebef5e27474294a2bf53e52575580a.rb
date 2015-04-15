class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError, "invalid nucleotide" unless Nucleotide.new(nucleotide).valid?
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotide.each_dna.with_object({}) do |nucleotide, tallies|
      tallies[nucleotide] = count(nucleotide)
    end
  end
end

class Nucleotide
  ADENINE  = "A"
  CYTOSINE = "C"
  GUANINE  = "G"
  THYMINE  = "T"
  URACIL   = "U"
  ALL = [ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL]
  DNA = ALL - [URACIL]

  def self.each_dna(&block)
    DNA.each(&block)
  end

  def initialize(code)
    @code = code
  end

  def valid?
    ALL.include?(@code)
  end
end
