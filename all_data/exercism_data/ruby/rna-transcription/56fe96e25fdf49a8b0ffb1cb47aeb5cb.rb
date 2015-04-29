class Complement
  def self.of_dna input
    input.chars.map{ |c| of_dna_nucleotide(c) }.reduce(:+)
  end

  def self.of_rna input
    input.chars.map{ |c| of_rna_nucleotide(c) }.reduce(:+)
  end

  private

    def self.of_rna_nucleotide input
      case input
      when 'C'
        return 'G'
      when 'G'
        return 'C'
      when 'U'
        return 'A'
      when 'A'
        return 'T'
      end
    end
    
    def self.of_dna_nucleotide input
      case input
      when 'A'
        return 'U'
      when 'G'
        return 'C'
      when 'C'
        return 'G'
      when 'T'
          return 'A'
      end
    end
end
