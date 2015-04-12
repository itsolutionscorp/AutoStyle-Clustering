class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @counts ||= words.each_with_object({}) do |word, counts|
      counts[word] ||= 0
      counts[word] += 1
    end
  end

  def words
    phrase.split(/[^a-zA-Z0-9']/).reject(&:empty?).map(&:downcase)
  end
end
