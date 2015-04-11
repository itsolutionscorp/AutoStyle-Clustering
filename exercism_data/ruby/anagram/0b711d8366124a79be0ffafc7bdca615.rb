class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match (candidates)     
    candidates.select do |candidate|
      candidate = candidate.downcase
      sort_word(candidate) == sort_word(@word) && candidate != @word
    end
  end

private

  def sort_word(word)
    word.chars.sort.join
  end
end
