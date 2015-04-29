class DNA

  def initialize(strand)
    @strand = strand
    validate(strand)
  end

  def validate(strand)
    validate_each_nucleotide(strand)
    rna_not_dna(strand)      
  end

  def validate_each_nucleotide(strand)
    argument_error(strand) unless strand.chars.all? do |nucleotide|
      valid_dna.include?(nucleotide)
    end 
  end

  def rna_not_dna(strand)
    argument_error(strand) if strand.chars.include?("U")
  end

  def count(nucleotide)
    argument_error(nucleotide) unless valid_nucleotides.include?(nucleotide)
    matches(nucleotide).count
  end

  def matches(nucleotide)
    strand.select { |letter| letter == nucleotide }
  end

  def nucleotide_counts
    {
     'A' => count('A'),
     'T' => count('T'),
     'C' => count('C'),
     'G' => count('G')
    }
  end

  def valid_nucleotides
    valid_dna + ["U"]
  end

  def valid_dna
     ["A","T","C","G"]
  end

  def strand
    @strand.chars
  end

  def argument_error(bad_input)
    raise ArgumentError.new("Sorry, #{bad_input} is not a valid.")
  end
end
