class Phrase
  def initialize(content)
    @content = content
  end

  def word_count
    words.each_with_object({}) do |word, counts|
      counts[word] = counts.fetch(word, 0) + 1
    end
  end

  def words
    @content.downcase.split /\W+/
  end
end
