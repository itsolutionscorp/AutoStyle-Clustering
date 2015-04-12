class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def words
    words = text.downcase.split(/\W+/)
  end

  def word_count
    data = Hash.new(0)
    words.each do |word|
      data[word] += 1
    end
    data
  end

end