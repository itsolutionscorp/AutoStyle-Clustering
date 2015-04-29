class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.keep_if { |c| anagram?(c.downcase) }
  end

  private

  def anagram?(candidate)
    not_equal?(candidate) &&
    equal_length?(candidate) &&
    matched_characters?(candidate)
  end

  def not_equal?(candidate)
    @word != candidate
  end

  def equal_length?(candidate)
    candidate.length == @word.length
  end

  def matched_characters?(candidate)
    candidate.chars.sort.join == @word.chars.sort.join
  end
end
