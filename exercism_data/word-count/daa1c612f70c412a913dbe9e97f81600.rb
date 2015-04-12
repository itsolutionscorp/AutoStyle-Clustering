class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new(0)
    downcased_phrase_words.each do |word|
      result[word] += 1
    end
    result
  end

  private

    def downcased_phrase_words
      @phrase.downcase.split(/\W+/)
    end
end
