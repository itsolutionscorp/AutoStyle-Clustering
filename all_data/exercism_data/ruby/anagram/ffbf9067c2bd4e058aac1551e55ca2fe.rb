class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    Array(anagrams).find_all do |candidate|
      @word != normalize(candidate) && sorted_normalize(@word) == sorted_normalize(candidate)
    end
  end

  private

  def sorted_normalize(word)
    word.downcase.chars.sort.join
  end

  def normalize(word)
    word.downcase
  end
end
