class Phrase

  def initialize(content)
    @content = content
  end

  def normalized_words
    @content.split(/[^\w]+/).map(&:downcase)
  end

  def word_count
    counts = Hash.new(0)

    normalized_words.each do |word|
      counts[word] += 1
    end

    counts
  end
end
