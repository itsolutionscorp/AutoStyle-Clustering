class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = extract_words_from_phrase
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  def extract_words_from_phrase
    @phrase.downcase.split(/[^A-Za-z0-9']/).reject(&:empty?)
  end
end
