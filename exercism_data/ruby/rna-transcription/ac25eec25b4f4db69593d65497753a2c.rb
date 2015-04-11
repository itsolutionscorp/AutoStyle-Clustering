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
