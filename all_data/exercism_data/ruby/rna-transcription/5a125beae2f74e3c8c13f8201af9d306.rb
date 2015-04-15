class Complement
  def self.of_dna(dna_string)
    rna_string = ''
    dna_string.length.times do |x|
      case dna_string[x]
        when 'C'
          rna_string << 'G'
        when 'G'
          rna_string << 'C'
        when 'T'
          rna_string << 'A'
        when 'A'
          rna_string << 'U'
      end
    end
    rna_string
  end

  def self.of_rna(rna_string)
    dna_string = ''
    rna_string.length.times do |x|
      case rna_string[x]
        when 'C'
          dna_string << 'G'
        when 'G'
          dna_string << 'C'
        when 'U'
          dna_string << 'A'
        when 'A'
          dna_string << 'T'
      end
    end
    dna_string
  end

end
