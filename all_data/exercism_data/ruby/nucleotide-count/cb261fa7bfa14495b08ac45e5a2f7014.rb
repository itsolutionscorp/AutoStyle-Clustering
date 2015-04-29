class DNA

  attr_reader :dna_strand

  def initialize(dna_strand)
    @dna_strand = verify(dna_strand)
  end

  def nucleotide_counts
    nucleotides = {
      "A" => count("A"),
      "T" => count("T"),
      "C" => count("C"),
      "G" => count("G")
    }
  end

  def count(nucleotide)
    dna_strand.count validate(nucleotide)
  end

  def validate(nucleotide)
    unless nucleotide.scan(/[^ATGCU]/).empty?
      raise ArgumentError.new("Not a valid nucleotide") 
    end
    nucleotide
  end

  def verify(dna)
    unless dna.scan(/[^ATCG]/).empty?
      raise ArgumentError.new("Not valid DNA")
    end
    dna    
  end

end
