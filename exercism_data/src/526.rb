class Phrase < Hash
  WORD = /\w+/i
  def initialize(text)
    text.scan(WORD).map do |word|
      word.downcase!
      self[word.to_s] = self.fetch(word.to_s, 0) + 1
    end
  end

  def word_count
    self
  end
end
