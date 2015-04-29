class DNA
  attr_reader :nucleotides

  def initialize(sequence)
    @nucleotides = sequence.chars
    raise ArgumentError unless valid_dna_sequence
  end

  def valid_dna_sequence
    nucleotides.all? do |nucleotide|
      valid_dna_nucleotides.include? nucleotide
    end
  end

  def count(nucleotide)
    unless valid_nucleotides.include? nucleotide
      raise ArgumentError
    end
    nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    {"A" => count("A"),
     "T" => count("T"),
     "C" => count("C"),
     "G" => count("G")
   }
  end 

  def valid_nucleotides
    %w(A T C G U)
  end

  def valid_dna_nucleotides
    %w(A T C G)
  end

end
