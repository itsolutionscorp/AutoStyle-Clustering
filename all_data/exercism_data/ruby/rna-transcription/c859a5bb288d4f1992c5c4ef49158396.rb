class Complement

  def self.of_dna(word)
    word.split(//).each_with_index { |a,i|
      case a
        when "C"
          word[i] = 'G'
        when "G"
          word[i] = 'C'
        when "A"
          word[i] = 'U'
        when "T"
          word[i] = 'A'
        when "U"
          raise(ArgumentError)
      end
    }
    word
  end
  def self.of_rna(word)
    word.split(//).each_with_index { |a,i|
      case a
        when 'G'
          word[i] = 'C'
        when 'C'
          word[i] ='G'
        when 'U'
          word[i] ='A'
        when 'A'
          word[i] ='T'
        when 'T'
          raise(ArgumentError)
        end
    }
    word
  end
end
