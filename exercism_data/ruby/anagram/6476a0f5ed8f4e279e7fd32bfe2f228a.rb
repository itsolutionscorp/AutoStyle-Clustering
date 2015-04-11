# class to find anagrams
class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    find_anagrams_from words
  end

  private

  def find_anagrams_from(words)
    sorted = words.each_with_object({}) { |w, res| res[w] = sortchars(w.downcase) }
    sorted.select { |k, v| sortchars(@word) == v && @word != k.downcase }.keys
  end

  def sortchars(word)
    word.chars.sort.join
  end
end
