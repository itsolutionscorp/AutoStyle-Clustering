class Phrase
  def initialize(text)
    @original_text = text
  end

  def word_count
    plain_words.each_with_object(Hash.new(0)) do |word, h|
      h[word] += 1
    end
  end

  private

  def plain_words
    @original_text.downcase.scan(/[[:alnum:]]+/)
  end
end
