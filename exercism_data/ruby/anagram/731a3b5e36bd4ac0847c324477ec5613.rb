class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |candidate| anagram?(candidate) }
  end

private

  def anagram?(candidate)
    same_length?(candidate) && same_letter_count?(candidate)
  end

  def same_length?(candidate)
    candidate.length == word.length
  end

  def same_letter_count?(candidate)
    word_letter_count == candidate_letter_count(candidate)
  end

  def word_letter_count
    @word_letter_count ||= word_count(word)
  end

  def candidate_letter_count(candidate)
    word_count(candidate)
  end

  def word_count(word_under_test)
    word_under_test.split(//).group_by { |letter| letter }
  end
end
