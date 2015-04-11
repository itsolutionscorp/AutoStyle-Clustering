class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count extract_words(@phrase)
  end

  private

  def extract_words(phrase)
    phrase.downcase.scan(/\w+/)
  end

  def count(words)
    words.each_with_object(Hash.new(0)) do |word, obj|
      obj[word] += 1
    end
  end
end
