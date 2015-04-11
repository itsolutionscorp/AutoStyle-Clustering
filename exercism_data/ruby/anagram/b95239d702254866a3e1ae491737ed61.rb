class Anagram
  class Word
    def initialize(str)
      @str = str
    end

    def letter_counts
      @letter_counts ||=
        letters.each_with_object(Hash.new(0)) { |letter, counts| counts[letter] += 1 }
    end

    private
    def letters
      @str.downcase.each_char
    end
  end

  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidates)
    candidates.select { |candidate|
      @word.letter_counts == Word.new(candidate).letter_counts
    }
  end
end
