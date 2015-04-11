class Anagram

  attr_reader :candidate

  def initialize(candidate)
    @candidate = sort_letters(candidate)
  end

  def match(words)
    words.select {|word| sort_letters(word) == candidate }
  end

  def sort_letters(word)
    word.chars.sort
  end

end
