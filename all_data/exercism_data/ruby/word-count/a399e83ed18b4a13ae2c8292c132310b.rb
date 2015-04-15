class Phrase

  def initialize(text)
    @text = text
  end

  def word_count

    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end

  end

  def normalized_text
    @text.downcase
  end

  def words
    normalized_text.scan(/[[:alnum:]]+/)
  end

end
