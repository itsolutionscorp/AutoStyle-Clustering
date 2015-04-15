class Anagram < SimpleDelegator

  def initialize(word)
    super word.downcase
  end

  def canonical
    chars.sort
  end

  def matches?(anagram)
    return if self == anagram
    anagram.canonical == canonical
  end

  def match(words)
    words.select do |word|
      matches? Anagram.new(word)
    end
  end
end
