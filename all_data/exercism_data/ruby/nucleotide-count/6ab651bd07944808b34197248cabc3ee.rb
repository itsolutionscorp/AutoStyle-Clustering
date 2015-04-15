class DNA

  attr_reader :dna

  def initialize(dna_input)
    @dna = verify(dna_input)
  end

  def nucleotide_counts
    nucleotides = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
    nucleotides.each_key { |key| nucleotides[key] += count(key.to_s) }
  end

  def count(nucleotide)
    dna.count validate(nucleotide)
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
