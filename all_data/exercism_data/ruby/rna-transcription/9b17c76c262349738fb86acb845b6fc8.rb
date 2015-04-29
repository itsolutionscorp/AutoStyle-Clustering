class Complement
  def self.of_dna(strand)
    strand.each_char.with_object(rna = "") { |nucleotide| rna << self.of_nucleotide(nucleotide, 'dna') }
  end

  def self.of_rna(strand)
    strand.each_char.with_object(dna = "") { |nucleotide| dna << self.of_nucleotide(nucleotide, 'rna') }
  end

  def self.of_nucleotide(nucleotide, type)
    case nucleotide
    when 'C'
      'G'
    when 'G'
      'C'
    when 'A'
      type == 'dna' ? 'U' : 'T'
    when 'T'
      raise ArgumentError if type == 'rna'
      'A'
    when 'U'
      raise ArgumentError if type == 'dna'
      'A'
    end
  end

end
