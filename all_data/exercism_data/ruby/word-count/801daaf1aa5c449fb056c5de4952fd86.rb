class Phrase
  def initialize(body)
    @body = body
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) do |word, results|
      results[word] += 1
    end
  end

  private

  def normalized_words
    @body.downcase.scan(/[a-zA-Z0-9]+/)
  end

end
