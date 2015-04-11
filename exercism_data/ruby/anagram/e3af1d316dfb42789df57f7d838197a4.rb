class String
  def anagram_of?(other)
    (letters == other.letters) && (self.downcase != other.downcase)
  end

  def letters
    self.downcase.chars.to_a.sort
  end
end

class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select{|word| word.anagram_of? @word }
  end
end
