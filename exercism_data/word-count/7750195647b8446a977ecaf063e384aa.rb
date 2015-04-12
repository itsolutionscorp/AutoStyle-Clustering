class Phrase

  attr_reader :phrase, :counts

  def initialize(phrase)
    @phrase = phrase
    @counts = word_count
  end

  def word_count
    phrase.split(/[^'\w]+/).each_with_object(Hash.new(0)) do |word, freq|
      key = word.downcase
      freq[key] += 1
    end
  end

end
