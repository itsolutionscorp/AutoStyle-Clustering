class Phrase

  def initialize(phrase)
    @phrase = phrase
    @extracted_words = extract_words
  end

  def word_count
    @extracted_words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

private
  def extract_words
    @phrase.downcase.scan(/\w+/)
  end

end
