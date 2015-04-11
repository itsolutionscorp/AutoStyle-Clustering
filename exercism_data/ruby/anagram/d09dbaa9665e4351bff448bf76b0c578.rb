class Anagram
  def initialize(word)
    @word = word
    @fingerprint = get_fingerprint word
  end

  def match(words)
    find_anagrams words
  end

  private

  attr_reader :word, :fingerprint

  def different_words?(word1, word2)
    word1.downcase != word2.downcase
  end

  def find_anagrams(words)
    words.select do |w|
      different_words?(w, word) && get_fingerprint(w) == fingerprint
    end
  end

  def get_fingerprint(word)
    word.downcase.chars.sort
  end
end
