class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    matches = sort_candidates(candidates).collect do |candidate|
      candidate[0] if candidate[1] == alphagram && !identical_word?(candidate[0])
    end.reject(&:nil?)
  end

  def identical_word?(input)
    input.downcase == word.downcase
  end

  def alphagram
    @alphagram ||= word.downcase.chars.sort.join
  end

  def sort_candidates(candidates)
     sorted_candidates = candidates.each_with_object(Hash.new(0)) do |c,sorted|
      sorted[c] = c.downcase.chars.sort.join
    end
    #binding.pry
    return sorted_candidates.to_a
  end

end
