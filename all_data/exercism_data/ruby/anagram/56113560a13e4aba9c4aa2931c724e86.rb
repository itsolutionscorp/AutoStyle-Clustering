class Word
  attr_reader :word
  def initialize(word)
    @word = word
  end

  def is_anagram_of?(word)
    sort_letters == word.sort_letters && self != word
  end

  def ==(other)
    @word == other.word.downcase
  end

  def sort_letters
    @word.downcase.chars.sort.join ' '
  end
end

class Anagram

  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    group_anagrams(words)[true] || []
  end

  private
  def group_anagrams(words)
    words.group_by { |word| @word.is_anagram_of?(Word.new(word)) }
  end
end
