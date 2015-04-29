class Word
  attr_reader :word
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

class Anagram

  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    group_anagrams(words)
  end

  private
  def group_anagrams(words)
    words.select { |word| @word.anagram_of?(Word.new(word)) }
  end
end
