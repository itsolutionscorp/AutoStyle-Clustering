class Complement
  def self.of_dna(strand_dna)
    (strand_dna.split"").map { |dna|
      case dna
      when 'C'
        'G'
      when 'G'
        'C'
      when 'T'
        'A'
      when 'A'
        'U'
      end
    }.join
  end

  def self.of_rna(strand_rna)
    (strand_rna.split"").map { |rna|
      case rna
      when 'C'
        'G'
      when 'G'
        'C'
      when 'U'
        'A'
      when 'A'
        'T'
      end
    }.join
  end
end
