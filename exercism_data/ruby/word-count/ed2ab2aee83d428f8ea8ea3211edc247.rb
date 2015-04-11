class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= get_word_count
  end

  private

  def get_word_count
    words_in_phrase.each_with_object(Hash.new(0)) do |word,count|
      count[word] += 1
    end
  end

  def words_in_phrase
    lowercased_phrase.scan(/\w+/)
  end

  def lowercased_phrase
    @phrase.downcase
  end
end
