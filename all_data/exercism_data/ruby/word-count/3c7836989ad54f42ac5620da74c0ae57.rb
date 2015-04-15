class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    Hash.new(0).tap do |count|
      words.each { |word| count[word] += 1 }
    end
  end

  def words
    text.downcase.split(/[^a-zA-Z0-9]+/)
  end
end
