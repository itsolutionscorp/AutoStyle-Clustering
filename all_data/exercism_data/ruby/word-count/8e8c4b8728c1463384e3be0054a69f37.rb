class Phrase
  attr_reader :sentence
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    counts = Hash.new
    words.each do |word|
      counts[word] = counts.fetch(word, 0) + 1
    end

    return counts
  end

  def words
    sentence.split(/\W/).reject(&:empty?).map(&:downcase)
  end
end
