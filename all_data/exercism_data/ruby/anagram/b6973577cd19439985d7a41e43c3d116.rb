class Anagram
  attr_reader :sorted_word, :word

  def initialize(word)
    @word = word.downcase
    @sorted_word = sort_letters(@word)
  end

  def sort_letters(word)
    word.chars.sort.join
  end

  def match(candidates)
    candidates.select do |candidate|
      candidate = candidate.downcase
      sort_letters(candidate) == sorted_word && candidate != word
    end
  end
end
