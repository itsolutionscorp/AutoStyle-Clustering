class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.select { |word| same_characters? word }
  end

  private

  def same_characters?(test_word)
    return false if @word == test_word.downcase
    char_count(@word) == char_count(test_word)
  end

  def char_count(word)
    word.downcase.split('').inject(Hash.new(0)) { |c,w| c[w] +=1; c }
  end

end
