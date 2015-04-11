class Phrase
  attr_accessor :text

  WORD_REGEX = /[^a-z0-9']/i

  def initialize(text)
    @text = text
  end

  def words
    text.split(WORD_REGEX).reject(&:empty?).map(&:downcase)
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word,dictionary|
      dictionary[word] += 1
    end
  end
end
