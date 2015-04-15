class Anagram

  attr_reader :target_word, :sorted_target
  def initialize target_word
    @target_word = target_word
    @sorted_target = downcase_and_sort(target_word)
  end

  def match words
    Array(words).select do |word|
      unique_of_target?(word) && downcase_and_sort(word) == sorted_target
    end
  end

  def unique_of_target?(other_word)
    other_word.downcase != target_word.downcase
  end

  def downcase_and_sort(word)
    word.downcase.chars.sort
  end
end
