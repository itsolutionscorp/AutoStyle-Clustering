class Anagram < Struct.new(:anagram)
  def match(words)
    Words[words].select {|word| word.anagram?(anagram) }.map(&:to_s)
  end
end

class Words
  include Enumerable

  def self.[](words)
    @words = words.map {|word| Word.new(word)}
  end
end

class Word < Struct.new(:word)
  def anagram?(other_word)
    word.downcase != other_word.downcase && word.downcase.chars.sort == other_word.downcase.chars.sort
  end

  def to_s
    word
  end
end
