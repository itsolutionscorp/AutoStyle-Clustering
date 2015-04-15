class Complement
  def self.of_dna(strand)
    strand.chars.each_with_index do |char, index|
      case char
        when 'G'
          strand[index] = 'C'
        when 'C'
          strand[index] = 'G'
        when 'T'
          strand[index] = 'A'
        when 'A'
          strand[index] = 'U'
      end
    end
    strand
  end

  def self.of_rna(strand)
    strand.chars.each_with_index do |char, index|
      case char
        when 'G'
          strand[index] = 'C'
        when 'C'
          strand[index] = 'G'
        when 'U'
          strand[index] = 'A'
        when 'A'
          strand[index] = 'T'
      end
    end
    strand
  end
end
