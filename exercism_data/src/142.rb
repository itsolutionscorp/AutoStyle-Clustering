class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.downcase.scan(/[a-z0-9]+/)
    words.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

end
