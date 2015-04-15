class DNA
  attr_reader :nucleotides

  ADENOSINE = ?A
  CYTOSINE  = ?C
  GUANINE   = ?G
  THYMINE   = ?T
  URACIL    = ?U

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    nucleotides.each_char.map do |nucleotide|
      case nucleotide
      when ADENOSINE, CYTOSINE, GUANINE then nucleotide
      when THYMINE then URACIL
      else raise ArgumentError, "Unknown nucleotide: #{nucleotide}"
      end
    end.join ''
  end

end
