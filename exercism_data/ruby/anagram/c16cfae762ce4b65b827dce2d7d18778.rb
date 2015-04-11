class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.select { |word| word if matches?(word.downcase) }
  end

  private

  def matches?(word)
    return false if word == @word || word.length != @word.length
    @word.split(//).sort == word.split(//).sort
  end
end
