class Anagram
  def initialize word
    @word = word.downcase
    @sorted_word = sort_letters @word
  end

  def match word_list
    word_list.select { |word| is_anagram? word.downcase }
  end

  private

  def is_anagram? word
    @word != word && @sorted_word == sort_letters(word)
  end

  def sort_letters word
    word.chars.sort
  end
end
