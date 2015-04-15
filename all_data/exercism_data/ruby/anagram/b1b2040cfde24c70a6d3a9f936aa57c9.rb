class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select {|candidate| is_anagram_of?(candidate)}
  end

  private

  def is_anagram_of?(candidate)
    downcased_candidate = candidate.downcase
    if (@word.length != candidate.length) || @word.eql?(downcased_candidate)
      false
    else
      character_histogram(@word) == character_histogram(downcased_candidate)
    end
  end

  def character_histogram(word)
    word.chars.to_a.inject(Hash.new(0)) do |histogram, character|
      histogram[character] += 1
      histogram
    end
  end

end
