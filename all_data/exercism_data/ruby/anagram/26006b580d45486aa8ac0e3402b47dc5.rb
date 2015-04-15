class Anagram
  def initialize(word)
    @word = word
  end

  def word_sort(word)
    output = []
    word.each_char do |char|
      output << char
    end
    output.sort
  end

  def match(test_array)
    output = []
    word = @word.downcase
    test_array.each do |element|
      test_word = element.downcase
      if word != test_word and word_sort(word) == word_sort(test_word)
        output.push(element)
      end
    end
    output
  end
end
