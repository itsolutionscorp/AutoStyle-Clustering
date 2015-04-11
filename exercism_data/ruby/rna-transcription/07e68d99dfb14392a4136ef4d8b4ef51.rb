class Complement
  def self.of_dna(strain)
    strain.chars.map do |nucl|
      of_dna_nucleotide(nucl)
    end.join
  end

  def self.of_dna_nucleotide(nucl)
    return 'G' if nucl == 'C'
    return 'C' if nucl == 'G'
    return 'A' if nucl == 'T'
    'U'
  end

  def self.of_rna(strain)
    strain.chars.map do |nucl|
      of_rna_nucleotide(nucl)
    end.join
  end

  def self.of_rna_nucleotide(nucl)
    return 'G' if nucl == 'C'
    return 'C' if nucl == 'G'
    return 'T' if nucl == 'A'
    'A'
  end
end
