class Phrase < Hash
  WORD = /\w+/i
  def initialize(text)
    text.scan(WORD).map do |word|
      word = word.to_s.downcase
      self[word] = self.fetch(word, 0) + 1
    end
  end

  def word_count
    self
  end
end
