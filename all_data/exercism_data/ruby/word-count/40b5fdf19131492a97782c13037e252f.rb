class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    stripped_phrase.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def stripped_phrase
    phrase.to_s.downcase.gsub(/[^a-z0-9\s]/, " ").strip.split(' ')
  end

end
