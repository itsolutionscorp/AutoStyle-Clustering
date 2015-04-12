class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    Hash.new.tap do |h|
      array_of_words.collect do |word|
        h[word] ||= 0
        h[word] += 1
      end
    end
  end

private

  def array_of_words
    @phrase.scan(/[\w\']+/).collect(&:downcase)
  end

end
