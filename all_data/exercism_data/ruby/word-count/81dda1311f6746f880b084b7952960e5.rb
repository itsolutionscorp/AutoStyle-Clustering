class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = {}

    words.each do |word|
      if counts.has_key? word
        counts[word] += 1
      else
        counts[word] = 1
      end
    end

    counts
  end

  private

  def words
    phrase.split(/\b/).reject { |w| w.match(/\W/) }.map(&:downcase)
  end

end
