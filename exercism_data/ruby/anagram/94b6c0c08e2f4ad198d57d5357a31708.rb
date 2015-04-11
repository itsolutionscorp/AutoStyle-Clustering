class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.select do |word|
      matches_word?(word.downcase)
    end
  end

  private

  def matches_word?(word)
    @word != word && @word.chars.sort == word.chars.sort
  end
end
