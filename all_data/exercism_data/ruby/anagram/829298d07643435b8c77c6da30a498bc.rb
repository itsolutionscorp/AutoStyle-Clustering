class Anagram
  def initialize(word)
    @subject_word = Word.new(word)
  end

  def match(words)
    words.select { |word|
      Word.new(word).anagram_of?(@subject_word)
    }
  end
end

class Word
  def initialize(text)
    @text = text
  end

  def anagram_of?(other)
    w1 = downcase
    w2 = other.downcase

    (w1.to_alphagram == w2.to_alphagram) && (w1 != w2)
  end

  protected
  attr_reader :text

  def downcase
    self.class.new(text.downcase)
  end

  def ==(other)
    text == other.text
  end

  def to_alphagram
    text.chars.sort.join('')
  end
end
