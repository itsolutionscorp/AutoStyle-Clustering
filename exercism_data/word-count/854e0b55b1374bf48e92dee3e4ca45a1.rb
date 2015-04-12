class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    words = divide_on_words(@string)
    words.frequency
  end

  private

  def divide_on_words(string)
    string.downcase.scan(/\w+'?\w+|\d+/)
  end
end

class Array
  def frequency
    reduce(Hash.new(0)) do |acc, el|
      acc[el] += 1
      acc
    end
  end
end
