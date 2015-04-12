class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.reduce(Hash.new(0)) do |counts, word|
      counts[word] = counts[word] + 1
      counts
    end
  end

  def words
    @text.downcase.split(/\W+/)
  end
end
