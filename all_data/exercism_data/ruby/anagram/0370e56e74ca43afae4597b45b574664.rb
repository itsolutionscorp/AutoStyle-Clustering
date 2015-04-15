class Anagram

  def initialize(phrase)
    @phrase = phrase.to_s
  end
  
  def match(wordlist)
    wordlist.find_all { |word| anagrammatize(@phrase) == anagrammatize(word) }
  end

  private

    def anagrammatize(word)
      word.downcase.chars.sort
    end

end
