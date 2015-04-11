class Phrase

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.inject(Hash.new(0)) {|h, w| h[w] += 1; h }
  end


  private

  def words
    normalize.split(' ')
  end

  def normalize
    text.downcase.gsub(/[^0-9a-z ]/, '')
  end
end
