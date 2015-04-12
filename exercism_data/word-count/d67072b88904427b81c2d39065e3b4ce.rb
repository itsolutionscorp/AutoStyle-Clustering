class Phrase

  attr_accessor :text, :word_array, :word_count

  def initialize(text)
    @text = text
    @word_array = creates_word_array
    @word_count ||= creates_word_hash
  end

  def creates_word_hash
    word_array.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def creates_word_array
    removes_punctuation(text.downcase).split(' ')
  end

  def removes_punctuation(text)
    text.tr("^a-zA-Z0-9'", ' ')
  end

end
