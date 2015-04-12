class Phrase
  def initialize text
    @text = text.downcase
  end

  def word_count
    words.inject Hash.new(0) do |hash, word|
      hash.merge word => hash[word] + 1
    end
  end

  private
  attr_reader :text

  def words
    text.split /[^a-z0-9]+/
  end
end
