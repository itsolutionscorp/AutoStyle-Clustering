class Phrase
  def initialize(phrase)
    @text = phrase
  end

  def word_count
    words_ignoring_case.each_with_object(Hash.new(0)) {|word, counts| counts[word] += 1}
  end

  def words_ignoring_case
    words_in @text.downcase
  end

  private

  def words_in(text)
    text.scan(/\w+/)
  end
end
