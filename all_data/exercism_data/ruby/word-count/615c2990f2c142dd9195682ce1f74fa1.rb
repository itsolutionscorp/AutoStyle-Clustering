class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[normalize_word(word)] += 1
    end
  end

  private

  def words
    @phrase.scan(/\w+/)
  end

  def normalize_word(word)
    word.downcase
  end
end
