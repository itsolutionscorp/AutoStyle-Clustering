class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    all_words.each_with_object(Hash.new(0)) { |word,hsh| hsh[word] += 1 }
  end

  private
    def all_words
      @phrase.downcase.scan(/\w+/)
    end

end
