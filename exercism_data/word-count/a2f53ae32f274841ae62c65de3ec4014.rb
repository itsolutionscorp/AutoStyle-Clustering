class Phrase

  attr_accessor :words, :word_count

  def initialize(text)
    @words = text.downcase.split(/[^a-zA-Z0-9']+/)
    @word_count ||= creates_word_hash
  end

  def creates_word_hash
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

end
