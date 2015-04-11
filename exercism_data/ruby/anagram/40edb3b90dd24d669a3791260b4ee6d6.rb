class Anagram
  def initialize(string)
    @normalized_word = normalized(string)
  end

  def match(word_list)
    word_list.select {|word| normalized(word) == @normalized_word }
  end

  private
  def normalized(string)
    string.downcase.chars.sort
  end
end
