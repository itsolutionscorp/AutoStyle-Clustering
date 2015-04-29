class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @phrase = phrase
    @word_count = make_word_count
  end

  private
  def downcase_words
    @downs ||= @phrase.downcase.scan(/['\w]+/)
  end

  def make_word_count
    downcase_words.each_with_object(Hash.new { 0 }) do |word, counts|
      counts[word] += 1
    end
  end
end
