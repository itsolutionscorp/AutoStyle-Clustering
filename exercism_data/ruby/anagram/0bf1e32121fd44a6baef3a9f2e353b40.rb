class Anagram
  def initialize(word)
    @normalized_word = normalized(word)
  end

  def match(words)
    word_list.select {|word| normalized(word) == @normalized_word }
  end

  private
  def normalized(word)
    word.downcase.chars.sort
  end
end
