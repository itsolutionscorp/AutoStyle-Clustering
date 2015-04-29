class Anagram
  def initialize(subject)
    @subject = Word.new(subject)
  end

  def match(candidates)
    candidates.select { |candidate|
      Word.new(candidate).anagram_of(@subject)
    }
  end
end


class Anagram::Word
  def initialize(word)
    @word = word
  end

  def anagram_of(other)
    letters == other.letters && word != other.word
  end

  protected

  def word
    @word.downcase
  end

  def letters
    word.chars.sort
  end
end
