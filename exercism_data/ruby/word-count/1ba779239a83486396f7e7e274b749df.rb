class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def words
    @words ||= clean_phrase.downcase.split
  end

  def clean_phrase
    @phrase.gsub(/[^a-zA-Z0-9' ]/, ' ')
  end
end
