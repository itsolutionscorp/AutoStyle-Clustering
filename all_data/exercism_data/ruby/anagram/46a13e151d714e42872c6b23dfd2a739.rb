class Anagram
  def initialize(word)
    @word = word
  end

  def match(matches)
    matches.select { |match| normalize(match) == normalize(@word) && match.downcase != @word.downcase }
  end

  def normalize(word)
    word.downcase.chars.sort
  end
end
