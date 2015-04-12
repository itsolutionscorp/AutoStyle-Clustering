class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    split_phrase.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1
    end
  end

  private
  def split_phrase
    @phrase.downcase.scan(/\w+/)
  end
end
