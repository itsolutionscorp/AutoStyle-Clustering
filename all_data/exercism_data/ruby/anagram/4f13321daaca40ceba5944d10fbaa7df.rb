class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.select do |anagram|
      matches?(anagram) && !identical?(anagram)
    end
  end

  private

  def matches?(anagram)
    sorted(word.downcase) == sorted(anagram.downcase)
  end

  def identical?(anagram)
    word.downcase == anagram.downcase
  end

  def sorted(word)
    word.chars.sort
  end
end
