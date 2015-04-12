class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_array.each_with_object({}) do |word, hash|
      hash[word] = word_array.count(word)
    end
  end

  private

  def word_array
    @word_array ||= 
      @phrase.downcase.scan(/\w+/)
  end

end
