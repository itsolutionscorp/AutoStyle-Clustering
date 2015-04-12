class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.downcase.split(/[^a-z0-9]/).reject{ |s| s.empty? }
    words.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

end
