class Phrase
  def initialize(text)
    self.text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private

  attr_accessor :text

  def words
    text.downcase.split(/\W+/)
  end
end
