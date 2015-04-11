# Computes complements to string representations of dna and rna
class Complement
  class << self
    def of_dna(strand)
      strand.chars.map { |char| convert_char_dna(char) }.join
    end

    def of_rna(strand)
      strand.chars.map { |char| convert_char_rna(char) }.join
    end

    private

    def convert_char_dna(char)
      case char
      when 'C'
        'G'
      when 'G'
        'C'
      when 'T'
        'A'
      when 'A'
        'U'
      end
    end

    def convert_char_rna(char)
      case char
      when 'C'
        'G'
      when 'G'
        'C'
      when 'U'
        'A'
      when 'A'
        'T'
      end
    end
  end
end
