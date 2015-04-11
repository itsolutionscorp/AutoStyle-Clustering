class Phrase
  def initialize content
    @content = content
  end

  def word_count
    words.reduce({}) do |count, word|
      count.merge word => 1 + count.fetch(word, 0)
    end
  end

  def words
    @content.downcase
            .split(/\W+/)
  end
end
