class Phrase
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words.scan(/\w+/).each_with_object(Hash.new(0)){|word, counter|
      counter[word] += 1 }
  end
 
  private

  def words
    sentence.downcase
  end
end
