class DNA

  def initialize(dna_entry)
    validate(dna_entry)
    @input = dna_entry
  end

  def validate(dna_entry)
    if invalid_nucleotides?(dna_entry) || rna?(dna_entry)
      raise ArgumentError, "Invalid input."
    end
  end

  def count(input)
    raise ArgumentError, "No such nucleotide." unless nucleotides.include? input
    @input.count(input)
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'C' => count('C'),
      'T' => count('T'),
      'G' => count('G'),
    }
  end

  private

  def rna?(dna_entry)
    dna_entry.include?('U')
  end

  def invalid_nucleotides?(dna_entry)
    dna_entry.chars.find { |letter| !nucleotides.include? letter }
  end

  def nucleotides
    ['A','C','T','G','U']
  end

end
