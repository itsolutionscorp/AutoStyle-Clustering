module Nucleotides
  def nucleotides
    [adenine, cytosine, thymine, guanine, uracil]
  end

  def adenine()  'A' end
  def cytosine() 'C' end
  def thymine()  'T' end
  def guanine()  'G' end
  def uracil()   'U' end
end

DNA = Struct.new(:sequence) do
  include Nucleotides

  def nucleotide_counts
    {
      adenine => count(adenine),
      cytosine => count(cytosine),
      thymine => count(thymine),
      guanine => count(guanine)
    }
  end

  def count(nucleotide)
    validate! nucleotide

    sequence.chars.count(nucleotide)
  end

  private

  def validate!(nucleotide)
    unless nucleotides.include? nucleotide
      raise ArgumentError, "#{nucleotide} is not a valid nucleotide"
    end
  end
end
