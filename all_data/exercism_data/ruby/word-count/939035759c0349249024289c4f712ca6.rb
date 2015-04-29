class Phrase
  PUNCTUATION = /[,:!&@$%\^]/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.gsub(PUNCTUATION, " ").split(" ").compact.map(&:downcase).each_with_object(Hash.new) { |word, hash|
      hash[word] = (hash[word] || 0) + 1
    }
  end
end
