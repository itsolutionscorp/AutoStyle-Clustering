class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(words)
    words.reject {|w|
      # Ignore the same word
      w.downcase == word.downcase 
    }.select {|w|
      # Find anagrams
      w.downcase.chars.sort == word.downcase.chars.sort
    }
  end
end
