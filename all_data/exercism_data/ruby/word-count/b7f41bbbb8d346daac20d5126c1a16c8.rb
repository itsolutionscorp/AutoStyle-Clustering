class Phrase

  attr_accessor :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    @word_count ||= word_count_hash
  end

  private

  def word_count_hash
    downcased_words.each_with_object(Hash.new(0)) { |word, cache| cache[word] += 1 }
  end

  def downcased_words
    words.map(&:downcase)
  end

  def words
    sentence.scan(/\w+/)
  end
end
