class Phrase

  attr_reader :text

  def initialize(text)
    @text = normalize(text)
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def normalize(string)
    string.downcase.gsub(/[^[:alnum:]]/, ' ')
  end

  def words
    text.split(/\s+/)
  end

end
