class Anagram
  def initialize(target_word)
    @target_word = target_word
  end

  def match(candidate_words)
    target_letter_counts = count_letters(target_word)

    candidate_words.select do |candidate|
      target_letter_counts == count_letters(candidate)
    end
  end

private
  attr_reader :target_word

  def count_letters(word)
    letter_counts = Hash.new(0)
    word.downcase.scan(/\w/) do |letter|
      letter_counts[letter] += 1
    end
    letter_counts
  end
end
