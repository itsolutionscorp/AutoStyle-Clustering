class Phrase

  def initialize words
    @words = normalize_and_remove_punctuation words
  end

  def word_count
    @words.each_with_object(Hash.new(0)) { |word, hash| hash[word]+= 1 }
  end

  private

  def normalize_and_remove_punctuation words
    words.downcase.scan(/\w+/)
  end

end