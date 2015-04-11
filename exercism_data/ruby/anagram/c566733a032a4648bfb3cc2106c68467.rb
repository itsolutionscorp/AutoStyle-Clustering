class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.select { |a| distinct_from?(a) && same_letters?(a) }
  end

  private

  def distinct_from?(anagram)
    @word.downcase != anagram.downcase
  end

  def same_letters?(anagram)
    sort_string(@word) == sort_string(anagram)
  end

  def sort_string(string)
    string.downcase.split('').sort
  end
end
