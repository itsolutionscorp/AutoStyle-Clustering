class Anagram

  def initialize(sample)
    @original = sample.downcase
  end

  def match(candidate)
    candidate.find_all{ |word| anagram? word.downcase }
  end

  private

  def anagram?(candidate)
    same_count?(candidate) && different_word?(candidate)
  end

  def same_count?(candidate)
    @original.chars.sort == candidate.chars.sort
  end

  def different_word?(candidate)
    @original != candidate
  end

end
