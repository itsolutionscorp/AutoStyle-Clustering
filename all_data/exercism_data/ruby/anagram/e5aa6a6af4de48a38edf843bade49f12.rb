class Anagram

  def initialize(word)
    @word = word;
  end

  def match(words)
    words.inject([]) do |anagrams, test_word| 
      if same_length_and_letters?(test_word, word)
        anagrams << test_word unless same_word?(test_word, word)
      end
      anagrams
    end
  end

  private

  attr_reader :word

  def tokenize(word)
    word.downcase.split('').sort
  end

  def same_letters?(word1, word2)
    tokenize(word1) == tokenize(word2)
  end

  def same_length?(word1, word2)
    word1.length == word2.length
  end

  def same_word?(word1, word2)
    word1.downcase == word2.downcase
  end

  def same_length_and_letters?(word1, word2)
    same_length?(word1, word2) && same_letters?(word1, word2)
  end

end
