class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count extract_words(@phrase)
  end

  private

  def extract_words(phrase)
    phrase.gsub(/\W/, " ").downcase.split
  end

  def count(words)
    word_count = Hash.new(0)
    words.each_with_object(word_count) {|i, a| a[i] += 1}
    word_count
  end
end
