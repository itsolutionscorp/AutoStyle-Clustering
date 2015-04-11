# Rna transcription

class Complement
  # convert dna to rna using each and a case statement
  def self.of_dna(dna)
    rna = ''
    dna.split('').each { |item|
      case item
        when 'G'
          rna += 'C'
        when 'C'
          rna += 'G'
        when 'T'
          rna += 'A'
        when 'A'
          rna += 'U'
      end
    }
    rna
  end

  # convert rna to dna using each and a case statement
  def self.of_rna(rna)
    dna = ''
    rna.split('').each { |item|
      case item
        when 'C'
          dna += 'G'
        when 'G'
          dna += 'C'
        when 'A'
          dna += 'T'
        when 'U'
          dna += 'A'
      end
    }
    dna
  end
end
