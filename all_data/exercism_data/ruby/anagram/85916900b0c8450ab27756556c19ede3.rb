class Anagram
  attr_reader :word

  def initialize word
    @word = word.downcase
  end

  def match candidates
    candidates.select { |candidate| is_anagram?(candidate.downcase) }
  end

  private

  def is_anagram? candidate
    eval_candidate(candidate) ? !equal_to_word?(candidate) : false
  end

  def eval_candidate candidate
    sort_candidate(word) == sort_candidate(candidate)
  end

  def sort_candidate candidate
    candidate.chars.sort
  end

  def equal_to_word? candidate
    candidate == word
  end

end
