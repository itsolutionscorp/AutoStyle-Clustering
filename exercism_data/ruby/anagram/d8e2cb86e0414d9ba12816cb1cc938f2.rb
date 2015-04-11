class Anagram
  def initialize(target)
    @target = to_anagram_key(target)
  end

  def match(words)
    words.select { |word| to_anagram_key(word) == @target }
  end

  private 

  def to_anagram_key(word)
    word.downcase.chars.sort
  end
end
