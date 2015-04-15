class Phrase
  def initialize(message)
    @message = message
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  private

  def normalized_words
    # words only, regardless of case - http://rubular.com/r/qvm4SeSHf4
    @message.downcase.scan(/\w+/)
  end
end
