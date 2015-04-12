class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.downcase.scan(/[[:alnum:]]+/)
    words.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
  end
end
