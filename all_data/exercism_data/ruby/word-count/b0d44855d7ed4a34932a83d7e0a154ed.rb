class Phrase

  def initialize(phrase)
    @seperated_words = extract_words phrase
  end

  def word_count
    count_each_word @seperated_words
  end

  private

  def extract_words(text)
    text.downcase.scan(/\w+/)
  end

  def count_each_word(text)
    text.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

end
