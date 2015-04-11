class Anagram < String
  def ==(word)
    letters(self) == letters(word)
  end

  def match(words)
    words.select { |word|
      self == word
    }
  end

  private

  def letters(word)
    word.to_s.split("").sort
  end
end
