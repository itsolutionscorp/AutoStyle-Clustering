class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.reduce(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end

  def words
    text.downcase.scan(/[0-9a-z']+/)
  end
end
