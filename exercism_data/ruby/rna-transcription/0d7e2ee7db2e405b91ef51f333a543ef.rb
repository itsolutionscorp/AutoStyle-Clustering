class Complement

  def self.of_dna(dna_sequence)
    dna_sequence.chars.map { |n| transcribe_dna(n) }.join
  end

  def self.of_rna(rna_sequence)
    rna_sequence.chars.map { |n| transcribe_rna(n) }.join
  end

  private

  def self.transcribe_dna(nucleotide)
    case nucleotide
    when 'A'
      'U'
    when 'C'
      'G'
    when 'G'
      'C'
    when 'T'
      'A'
    end
  end

  def self.transcribe_rna(nucleotide)
    case nucleotide
    when 'U'
      'A'
    when 'C'
      'G'
    when 'G'
      'C'
    when 'A'
      'T'
    end
  end

end
