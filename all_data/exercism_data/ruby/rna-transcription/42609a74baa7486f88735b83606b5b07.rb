class Complement

  def self.of_dna(dna)
    rna_trans = []
    dna.chars.each do |x|
      case
      when x == 'G'
        rna_trans.push('C')
      when x == 'C'
        rna_trans.push('G')
      when x == 'T'
        rna_trans.push('A')
      when x == 'A'
        rna_trans.push('U')
      end
    end
    rna_trans.join('')
  end

  def self.of_rna(rna)
    dna_trans = []
    rna.chars.each do |x|
      case
      when x == 'C'
        dna_trans.push('G')
      when x == 'G'
        dna_trans.push('C')
      when x == 'A'
        dna_trans.push('T')
      when x == 'U'
        dna_trans.push('A')
      end
    end
    dna_trans.join('')
  end

end
