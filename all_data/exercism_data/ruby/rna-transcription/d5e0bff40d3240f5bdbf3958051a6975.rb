class Complement

  def self.of_dna(input)
    complement_generator(input, 'dna')
  end

  def self.of_rna(input)
    complement_generator(input, 'rna')
  end

  private

  def self.complement_generator(input, type)

    input = input.split(//)
    strand = []
    input.map do |nucleotide|
      if type == 'dna'
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
      else
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
    end
    strand.join
  end

end
