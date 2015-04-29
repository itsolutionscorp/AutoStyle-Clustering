class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      WordPair.new(word, candidate).anagrams?
    end
  end

  class WordPair
    attr_reader :word1, :word2

    def initialize(*words)
      @word1, @word2 = words.map(&:downcase)
    end

    def anagrams?
      word1 != word2 && word1.chars.sort == word2.chars.sort
    end
  end
end
