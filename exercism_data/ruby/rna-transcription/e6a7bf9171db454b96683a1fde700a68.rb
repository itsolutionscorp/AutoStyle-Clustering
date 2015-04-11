class Complement
  def self.of_nucleotide(nucleotide, type)
    case nucleotide
      when 'G'
        'C'
      when 'C'
        'G'
      when 'T'
        'A'
      when 'A'
        type == :rna ? 'T' : 'U'
      when 'U'
        'A'
    end
  end

  def self.compute(strand, type)
    nucleotides = strand.chars
    nucleotides.map do |nucleotide|
      Complement.of_nucleotide(nucleotide, type)
    end.join('')
  end
  def self.of_dna(strand)
    Complement.compute(strand, :dna)
  end

  def self.of_rna(strand)
    Complement.compute(strand, :rna)
  end
end
