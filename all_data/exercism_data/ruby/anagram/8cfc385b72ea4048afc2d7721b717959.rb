class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    sorted_candidates = sort_candidates(candidates)
    matches = sorted_candidates.select do |candidate|
      candidate[0] if identical_word?(candidate[0]) && candidate[1] == downcase_and_sort_word
    end
  end

  def identical_word?(input)
    input.downcase == word.downcase
  end

  def downcase_and_sort_word
    word.downcase.chars.sort.join
  end

  def sort_candidates(candidates)
     candidates.each_with_object(Hash.new(0)) do |c,sorted|
      sorted[c] = c.downcase.chars.sort.join
    end
  end

end
