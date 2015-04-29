class Anagram

  def initialize( word )
    @base_word = word
    @sorted_base_word = word.downcase.chars.sort.join
  end

  def match( word_list )
    return word_list.match_words( @base_word ).match_cases( @base_word )
  end


  class ::Array
    def match_words( base_word )
      anagram_list = []
      self.each do |word|
        anagram_list << word  if (word.downcase != base_word.downcase) && (word.downcase_sort == base_word.downcase_sort)
      end
      return anagram_list.uniq
    end

    def match_cases ( base_word )
      return [] if self.empty? 
      case_senesitive_sequence_for_base_word = base_word.convert_case_to_numbers
      self.each do |anagram_word|
        self.delete( anagram_word ) if anagram_word.convert_case_to_numbers != case_senesitive_sequence_for_base_word
      end
    end
  end


  class ::String
    def downcase_sort
      return self.downcase.chars.sort.join  
    end

    def convert_case_to_numbers
      # Capital letters converted to "1"
      # Lower case converted to "0"
      converted_sequence = ""
      self.chars.each do |letter|
        letter.capital? ? converted_sequence << "1" : converted_sequence << "0"
      end
      converted_sequence
    end
    
    def capital?
      self == self.upcase ? true : false
    end
  end
end
