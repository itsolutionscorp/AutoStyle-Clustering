class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = letters(@word)
  end

  def match(word_list)
    word_list.select { |w| w.downcase != @word && letters(w) == @letters }
  end

  private

  def letters(word)
    word.downcase.split(//).sort
  end
end
