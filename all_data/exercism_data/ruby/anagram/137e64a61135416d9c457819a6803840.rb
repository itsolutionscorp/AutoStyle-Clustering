class Anagram
  def initialize(word)
    @word = word
  end

  def create_word_array(word)
    word_array = []
    word.each_char do |char|
      word_array << char
    end
    word_array.sort
  end

  def match(test_array)
    output = []
    test_array.each do |element|
      test_word = element.downcase
      word = @word.downcase
      if word != test_word and create_word_array(word) == create_word_array(test_word)
        output.push(element)
      end
    end
    output
  end
end
