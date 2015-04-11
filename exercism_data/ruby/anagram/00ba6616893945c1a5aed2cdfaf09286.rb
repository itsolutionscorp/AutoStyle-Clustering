class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.delete_if(&duplicate).select(&anagram)
  end

  private

  def duplicate
    ->(other) { @word == other.downcase }
  end

  def anagram
    ->(other) { encode(@word) == encode(other.downcase) }
  end

  def encode(element)
    element.chars.sort.join
  end
end
