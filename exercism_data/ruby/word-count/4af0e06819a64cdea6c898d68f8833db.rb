class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    @word_count = count_words(phrase)
  end

  def count_words(phrase)
    words = phrase.downcase.scan(/[\w\']+/)
    words.each_with_object(Hash.new 0) do |word, count|
      count[word] += 1
    end
  end
end
