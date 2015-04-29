class Anagram
  def initialize(word)
    @target = word
  end

  def match(candidate_words)
    candidate_words.select { |word| anagrams? word, @target }
  end

  def anagrams?(word1, word2)
    word1, word2 = normalize(word1), normalize(word2)
    to_comparable(word1) == to_comparable(word2) unless word1 == word2
  end

  def normalize(word)
    word.downcase
  end

  def to_comparable(word)
    word.split('').sort.join('')
  end
end
