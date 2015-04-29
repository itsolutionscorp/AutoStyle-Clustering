class Anagram < Struct.new(:word)

  def match(word_list)
     word_list.select do|w| 
       if word.downcase == w.downcase
         next
       end
       word if alphabetize(word) == alphabetize(w)
     end
  end

  private

  def alphabetize(word)
     word.downcase.chars.sort
  end
end
