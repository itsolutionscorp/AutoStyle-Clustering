class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    extract_words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

private
  def extract_words
    @phrase.downcase.scan(/\w+/)
  end

end
