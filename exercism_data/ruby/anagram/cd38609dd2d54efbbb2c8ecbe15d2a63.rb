class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    @words = words
    duplicate
    filter
  end

  private

  def duplicate
    @words.delete_if { |w| w.downcase == @word }
  end

  def filter
    @words.select { |w| encode(w.downcase) == encode(@word) }
  end

  def encode(element)
    element.chars.sort.join
  end
end
