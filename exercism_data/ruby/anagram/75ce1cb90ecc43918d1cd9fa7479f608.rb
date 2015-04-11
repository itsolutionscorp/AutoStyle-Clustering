class Anagram
  def initialize(word)
    @word = word.downcase
    @word_count = char_count(@word)
  end

  def match(list)
    list.select { |word| is_an_anagram? word.downcase }
  end

  def is_an_anagram?(test_word)
    return false if @word == test_word
    @word_count == char_count(test_word)
  end

  def char_count(word)
    word.split('').inject(Hash.new(0)) { |c,w| c[w] +=1; c }
  end
end
