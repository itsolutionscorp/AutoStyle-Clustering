class Phrase
  attr_reader :word_count
 
  def initialize(input)
    @word_count = count_words_in(input)
  end
 
  def count_words(input)
    words(input).each_with_object(Hash.new(0)) do |word, collection|
      collection[word] += 1
    end
  end
 
  def words(input)
    input.downcase.scan(/\w+/)
  end
end
