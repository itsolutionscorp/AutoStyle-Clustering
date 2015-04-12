class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||=
      downcase_words.each_with_object(Hash.new 0) do |word, count|
        count[word] += 1
      end
  end

  private
  def downcase_words
    @downcase_words ||= @phrase.downcase.scan(/['\w]+/)
  end
end
