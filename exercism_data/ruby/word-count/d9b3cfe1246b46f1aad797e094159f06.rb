class Phrase
  def initialize(string)
    @original_string = string
  end

  def word_count
    array_of_words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def array_of_words
    @original_string.downcase.scan(/\w+/)
  end
end
