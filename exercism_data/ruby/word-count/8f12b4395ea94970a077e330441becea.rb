class Phrase
  def initialize(sentence)
    @words = clean_sentence(sentence).split(' ')
  end

  def word_count
    @words.each_with_object(Hash.new(0)){|word,counts| counts[word] += 1}
  end

  private

  def clean_sentence(sentence)
    sentence.gsub(/[^\w' ]/i, ' ').strip.downcase
  end
end
