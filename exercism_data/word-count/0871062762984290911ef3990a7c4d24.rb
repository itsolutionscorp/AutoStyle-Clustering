class Phrase
  def initialize(content)
    @content = content
  end

  def word_count
    words.inject({}) do |counts, word|
      counts[word] = counts.fetch(word, 0) + 1
      counts
    end
  end

  def words
    @content.downcase.split /\W+/
  end
end
