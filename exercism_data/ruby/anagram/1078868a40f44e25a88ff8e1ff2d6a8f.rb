class Anagram

  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(list)
    list.select{ |candidate| word.anagram?(Word.new(candidate)) }
  end

end

class Word
  
  attr_accessor :word, :normal, :canonical

  def initialize(word)
    @word = word
  end

  def normal_form
    normal ||= word.downcase
  end

  def canonical_form
    canonical ||= normal_form.chars.sort
  end

  def anagram?(other)
    normal_form != other.normal_form && canonical_form == other.canonical_form
  end

end
