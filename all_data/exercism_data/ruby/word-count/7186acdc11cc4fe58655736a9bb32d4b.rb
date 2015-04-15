class Phrase
  def initialize(phrase)
    sanatized_phrase = sanatize_and_normalize(phrase)
    @words = sanatized_phrase.scan(/\w+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word,h|
      h[word] += 1
    end
  end

  private

  def sanatize_and_normalize(words)
    words.downcase.gsub(/[^\w\s]+/, ' ')
  end
end
