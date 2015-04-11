class Word
  attr_reader :word

  def initialize(word)
    @word = word.downcase
    freeze
  end

  def anagram_of?(word)
    alphagranize == word.alphagranize && self != word
  end

  def ==(other)
    @word == other.word
  end

  def alphagranize
    @word.chars.sort.join ' '
  end
end

class Words

  def initialize(words)
    @words = words 
  end

  def group_anagrams(word)
    @words.select { |w| word.anagram_of?(Word.new(w)) }
  end

end

class Anagram

  def initialize(word)
    @word = Word.new word
  end

  def match(words)
    Words.new(words).group_anagrams(@word)
  end

end
