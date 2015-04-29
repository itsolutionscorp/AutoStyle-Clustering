class Word
  attr_reader :word

  def initialize(word, other)
    @word = word
    @other = other
  end

  def match?
    distinct_from? && same_letters?
  end

  private

  def distinct_from?
    @word.downcase != @other.downcase
  end

  def same_letters?
    sort_string(@word) == sort_string(@other)
  end

  def sort_string(string)
    string.downcase.split('').sort.join ''
  end
end

class Anagram
  def initialize(input)
    @input = input
  end

  def match(dictionary)
    words = dictionary.map { |w| Word.new w, @input }
    words.select(&:match?).map &:word
  end
end
