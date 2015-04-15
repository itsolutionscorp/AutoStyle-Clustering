class Complement

  def self.of_dna(input)
    input = input.split(//)
    strand = []
    input.map do |nucleotide|
      case nucleotide
        when 'C'
          strand << 'G'
        when 'G'
          strand << 'C'
        when 'A'
          strand << 'U'
        when 'T'
          strand << 'A'
      end
    end
    strand.join
  end

  def self.of_rna(input)
    input = input.split(//)
    strand = []
    input.map do |nucleotide|
      case nucleotide
        when 'G'
          strand << 'C'
        when 'C'
          strand << 'G'
        when 'U'
          strand << 'A'
        when 'A'
          strand << 'T'
      end
    end
    strand.join
  end
end
