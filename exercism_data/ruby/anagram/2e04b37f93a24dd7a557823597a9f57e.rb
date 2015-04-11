class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = get_sorted_letters(word)
  end

  def match(list)
    list.select do |word|
      word.downcase != @word && get_sorted_letters(word) == @letters
    end
  end

  def get_sorted_letters(word)
    word.downcase.split('').sort
  end
end
