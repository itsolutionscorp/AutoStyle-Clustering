class Anagram
  attr_accessor :word

  def initialize(word)
    @word = word
  end

  def match(word_list)
     word_list.select do|w| 
       if word.downcase == w.downcase
         next
       end
       alphabetize(word) == alphabetize(w)
     end
  end

  private

  def alphabetize(word)
     word.downcase.chars.sort
  end
end
