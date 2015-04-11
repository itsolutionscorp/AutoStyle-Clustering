class Anagram

  def initialize(word)
    @word = word
  end

  def match(choices)
    choices.group_by(&hash).fetch(key, []).reject(&identical)
  end

  private

  def key
    @key ||= hash.(word)
  end

  def hash
    -> word { word.downcase.split('').sort }
  end

  def identical
    -> match { match.downcase == word.downcase }
  end

  attr_reader :word
end
