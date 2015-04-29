class Anagram
  def initialize(original_word)
    @original_word = original_word
    @original_sorted_word = sort(@original_word)
    @already_considered = [@original_word.downcase]
  end

  def match(array_of_words)
    array_of_words.select do |word|
      matching_word?(word) && not_already_considered?(word)
    end
  end

  private

  def matching_word?(word)
    sort(word) == @original_sorted_word
  end

  def not_already_considered?(word)
    !@already_considered.include?(word.downcase)
  end

  def sort(word)
    word.downcase.split('').sort.join
  end
end
