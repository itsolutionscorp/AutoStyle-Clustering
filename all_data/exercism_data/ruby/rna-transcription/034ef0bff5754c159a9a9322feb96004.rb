# If there is a DNA class, there obviously needs to be a RNA class. Since both classes seem to share the same behaviour and data, the next logical step for me was to move everything in a parent class.

# What bothers me a bit is that #to_rna has to return a String. That would not match my expectations, since I'd expect a RNA instance - to_a, to_i etc. always return an Array or an Integer, never a String.

# -chris

# P.S. I cannot nitpick my own exercise, even though there is a field for it - now looking for a github issue ;)

class Macromolecule
  def initialize(nucleotide)
    @nucleotide = nucleotide
  end

  def to_rna
    transformed_nucleotide = @nucleotide.gsub("T", "U")
    RNA.new(transformed_nucleotide).to_s # I'd rather not return a string here but the actual expected "RNA" object, but well...
  end

  def to_dna
    transformed_nucleotide = @nucleotide.gsub("U", "T")
    DNA.new(transformed_nucleotide).to_s
  end

  def to_s
    @nucleotide
  end
  alias_method :to_str, :to_s
end

class DNA < Macromolecule
end

class RNA < Macromolecule
end
