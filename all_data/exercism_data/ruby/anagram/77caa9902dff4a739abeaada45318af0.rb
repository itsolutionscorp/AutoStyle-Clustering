class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = letters(@word)
  end

  def match(word_list)
    word_list.select do |w|
      word = w.downcase
      word != @word && letters(word) == @letters
    end
  end

  private

  def letters(word)
    word.split(//).sort
  end
end
