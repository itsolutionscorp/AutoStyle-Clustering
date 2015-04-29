# complement.rb
# author: Ray Wach
# date: 2015-01-05

class Complement
  def self.of_dna(sequence)
    complement = ""
    sequence.chars do |c|
      case c
      when 'G'
        complement << 'C'
      when 'C'
        complement << 'G'
      when 'T'
        complement << 'A'
      when 'A'
        complement << 'U'
      end
    end
    return complement
  end

  def self.of_rna(sequence)
    complement = ""
    sequence.chars do |c|
      case c
      when 'C'
        complement << 'G'
      when 'G'
        complement << 'C'
      when 'A'
        complement << 'T'
      when 'U'
        complement << 'A'
      end
    end
    return complement
  end
end
