class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.select do |anagram|
      next if duplicated?(anagram)
      normalize(word) == normalize(anagram)
    end
  end

  private

  def duplicated?(s)
    word.downcase == s.downcase
  end

  def normalize(s)
    s.downcase.chars.sort
  end

end
