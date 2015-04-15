class Complement
  def self.of_dna(nucleotide)
    map_complements('rna', nucleotide)
  end

  def self.of_rna(nucleotide)
    map_complements('dna', nucleotide)
  end

  private
  def self.rules
    { 
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.map_complements(type, nucleotide)
    complements = nucleotide.chars.map do |nuc|
      case type
        when 'rna'
          rna_complement(nuc)
        when 'dna'
          dna_complement(nuc)
      end
    end
    complements.join
  end

  def self.rna_complement(nucleotide)
    rules[nucleotide]
  end

  def self.dna_complement(nucleotide)
    rules.key(nucleotide)
  end
end
