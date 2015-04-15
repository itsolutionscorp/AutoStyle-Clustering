class Phrase
  def initialize phrase
    @phrase = phrase.downcase
  end

  def word_count
    uniq_words.each_with_object({}) { |word, wh| wh[word] = count_for word }
  end

  private

  def uniq_words
    split_phrase.uniq
  end

  def split_phrase
    @split_phrase ||= @phrase.scan /\w+/
  end

  def count_for word
    split_phrase.count word
  end
end
