class Phrase
  def initialize(phrase)
    @words = extract_words(phrase)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) {|word, counts| counts[word] = counts[word] + 1}
  end

  private

  def extract_words(phrase)
    split_in_words phrase.downcase
  end

  def split_in_words(phrase)
    phrase.scan(/\w+/)
  end
end
