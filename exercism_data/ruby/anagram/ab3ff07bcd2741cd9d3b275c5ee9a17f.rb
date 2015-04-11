class Word < String
  def letters
    downcase.split('')
  end

  def same? word
    self.downcase == word.downcase
  end

  def same_letters? word
    Word.new(word).letters.sort == letters.sort
  end

  def anagram? word
    (not same? word) && same_letters?(word)
  end
end

class Anagram
  def initialize(raw_word)
    @word = Word.new(raw_word)
  end
  def match(chocies)
    chocies.each_with_object([]) do |choice, matches|
      matches << choice if @word.anagram? choice
    end
  end
  
  
end
