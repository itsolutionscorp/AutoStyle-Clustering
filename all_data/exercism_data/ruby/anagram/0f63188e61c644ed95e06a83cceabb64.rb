class Anagram
  def initialize(target)
    @target = target.to_anagram_key
  end

  def match(words)
    words.select { |word| word.to_anagram_key == @target }
  end
end

class String
  def to_anagram_key
    self.downcase.chars.sort
  end
end
