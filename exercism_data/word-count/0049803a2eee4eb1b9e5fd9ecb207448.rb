class Phrase
  def initialize(phrase)
    @text = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) {|word, counts| counts[word] += 1}
  end

  def words
    split_in_words @text.downcase
  end

  private

  def split_in_words(text)
    text.scan(/\w+/)
  end
end
