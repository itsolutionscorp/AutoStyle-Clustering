class Complement
  def self.of_dna(dna)
    nucleotides = dna.split('')
    nucleotides.inject('') do |string, nucleotide|
      case nucleotide
      when 'C'
        string << 'G'
      when 'G'
        string << 'C'
      when 'T'
        string << 'A'
      when 'A'
        string << 'U'
      end
    end
  end

  def self.of_rna(rna)
    nucleotides = rna.split('')
    nucleotides.inject('') do |string, nucleotide|
      case nucleotide
      when 'C'
        string << 'G'
      when 'G'
        string << 'C'
      when 'U'
        string << 'A'
      when 'A'
        string << 'T'
      end
    end
  end
end
