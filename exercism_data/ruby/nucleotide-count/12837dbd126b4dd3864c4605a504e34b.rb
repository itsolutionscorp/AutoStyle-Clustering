class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'C' => count('C'),
      'G' => count('G'),
      'T' => count('T'),
    }
  end

  def count(symbol)
    nucleobase = Nucleotide.new(symbol)
    strand.chars.count(nucleobase)
  end
  
end

class Nucleotide < String

  GUANINE  = 'G'
  ADENINE  = 'A'
  THYMINE  = 'T'
  CYTOSINE = 'C'
  URACIL   = 'U'

  def initialize(symbol)
    unless nucleotide_symbol?(symbol)
      raise ArgumentError, "#{symbol} is not a nucleotide symbol"
    end
    super
  end

  def nucleotide_symbol?(symbol)
    [GUANINE, ADENINE, THYMINE, CYTOSINE, URACIL].include?(symbol)
  end


end
