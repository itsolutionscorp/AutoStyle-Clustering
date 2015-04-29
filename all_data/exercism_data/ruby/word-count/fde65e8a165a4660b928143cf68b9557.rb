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
    h = Hash.new(0)
    words.each { |k| h[k] += 1}
    words.keys.map{ |k| k ["count"] = h[k]; k}
    #words.each_with_object({}) { |(k, v), h| h[k] = words.count(k) }
  end

end
