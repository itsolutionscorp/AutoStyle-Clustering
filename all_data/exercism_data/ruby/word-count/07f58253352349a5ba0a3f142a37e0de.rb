class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= begin
      downcased_words.each_with_object(Hash.new 0) do |word, count|
        count[word] += 1
      end
    end
  end

  private

  def downcased_words
    @downcased_words ||= @phrase.downcase.scan(/['\w]+/)
  end
end
