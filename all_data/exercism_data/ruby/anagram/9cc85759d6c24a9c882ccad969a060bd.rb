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
      (@histogram ||= character_histogram(@word)) == character_histogram(downcased_candidate)
    end
  end

  def character_histogram(word)
    histogram = Hash.new(0)
    word.chars.each {|character| histogram[character] += 1 }
    histogram
  end

end
