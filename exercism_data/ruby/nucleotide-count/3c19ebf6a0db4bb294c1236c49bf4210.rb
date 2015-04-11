class DNA

  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = verify(dna_string)
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
    dna_string.count validate(nucleotide)
  end

  def validate(nucleotide)
    unless nucleotide.scan(/[^ATGCU]/).empty?
      raise ArgumentError.new("Not a valid nucleotide") 
    end
    nucleotide
  end

  def verify(input)
    unless input.scan(/[^ATCG]/).empty?
      raise ArgumentError.new("Not valid DNA")
    end
    input    
  end

end
