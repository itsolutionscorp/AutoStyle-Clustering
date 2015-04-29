class Anagram
  def initialize(word)
    @word = word.downcase
    @word_sorted = @word.chars.sort
  end

  def match(list)
    list.select {|element| anagram?(element.downcase)}
  end

  private

  def anagram?(string)
    string.chars.sort == @word_sorted && @word != string
  end

end
