class Word
  attr_reader :word

  def self.with(word)
    new(word)
  end

  def initialize(word)
    @word = word
  end

  def anagram_of?(word)
    alphagranize == word.alphagranize && self != word
  end

  def ==(other)
    @word == other.word.downcase
  end

  def alphagranize
    @word.downcase.chars.sort.join ' '
  end
end

class Words

  def self.for(words)
    new(words)
  end

  def initialize(words)
    @words = words 
  end

  def group_anagrams(word)
    @words.select { |w| word.anagram_of?(Word.new(w)) }
  end

end

class Anagram

  def initialize(word)
    @word = Word.with word
  end

  def match(words)
    Words.for(words).group_anagrams(@word)
  end

end
