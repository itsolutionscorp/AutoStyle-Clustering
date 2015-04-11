# class to find anagrams
class Anagram
  def initialize(word)
    @w = word
  end

  def match(words)
    words.reject { |w| w.casecmp(@w).zero? }.select { |w| sort(@w) == sort(w) }
  end

  private

  def sort(word)
    word.downcase.chars.sort
  end
end
