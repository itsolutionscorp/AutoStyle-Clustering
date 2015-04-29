class Anagram < Struct.new(:word)

  def match(word_list)
     word_list.select do|w| 
       word if alphabetize(word) == alphabetize(w)
     end
  end

  private

  def alphabetize(word)
     word.downcase.chars.sort
  end
end
