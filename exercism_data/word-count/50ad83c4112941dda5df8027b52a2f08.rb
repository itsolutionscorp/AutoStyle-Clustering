class Phrase

  attr_accessor :words

  def initialize words
    normalize_and_remove_punctuation(words)
  end

  def word_count
    return_word_hash
  end

  private

  def normalize_and_remove_punctuation words
    @words = words.downcase.scan(/\w+/)
  end

  def return_word_hash
    words.each do |word|
      x = words.count(word)
      @hash = Hash[@words.map{ |word| [word, words.count(word)]} ]
    end
    @hash
  end

end
