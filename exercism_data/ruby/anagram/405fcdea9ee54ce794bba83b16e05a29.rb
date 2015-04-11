class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    normalize_list(anagrams).find_all do |candidate|
      @word != candidate && normalize(@word) == normalize(candidate)
    end
  end

  private
  def normalize(word)
    word.downcase.chars.sort.join
  end

  def normalize_list(list)
    Array(list).map(&:downcase)
  end
end
