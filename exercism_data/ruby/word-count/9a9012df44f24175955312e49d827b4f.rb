class Phrase

  def initialize words
    @words = normalize_and_remove_punctuation words
  end

  def word_count
    return_word_hash
  end

  private

  attr_accessor :words

  def normalize_and_remove_punctuation words
    words.downcase.scan(/\w+/)
  end

  def return_word_hash
   words.inject({}) {|key, v| key[v] = words.count(v); key}
  end

end
