class Word
  attr_reader :raw

  def initialize(raw)
    @raw = raw
  end

  def letters
    @raw.downcase.chars
  end

  def sorted_letters
    letters.sort
  end

  def ==(target)
    @raw.downcase == target.raw.downcase
  end
end

class Anagram
  def initialize(original_word)
    @original_word = Word.new(original_word)
  end

  def match(words)
    words.select do |w|
      target_word = Word.new(w)
      (@original_word.sorted_letters == target_word.sorted_letters) && @original_word != target_word
    end
  end
end
