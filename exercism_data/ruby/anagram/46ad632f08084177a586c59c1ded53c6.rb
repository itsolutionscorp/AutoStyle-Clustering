module Process

  def self.alphabetize(word)
    word.downcase.chars.sort
  end
end

class Anagram

  def initialize(word)
    @letters = Process.alphabetize(word)
  end

  def match(word_list)
    word_list.select {|some_word| is_anagram?(some_word)}
  end

private

  def is_anagram?(some_word)
    Process.alphabetize(some_word) == @letters
  end

end
