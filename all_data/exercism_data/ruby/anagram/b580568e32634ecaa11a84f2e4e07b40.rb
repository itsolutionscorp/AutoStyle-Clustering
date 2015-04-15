class Anagram
  def initialize(word)
    @word = word.strip.downcase
  end

  def match(candidates)
    candidates.
      reject {|c| c == @word }.
      select { |w| sort_word(w) == sort_word(@word) }
  end

  private

  def sort_word(word)
    word.chars.sort.join
  end
end
