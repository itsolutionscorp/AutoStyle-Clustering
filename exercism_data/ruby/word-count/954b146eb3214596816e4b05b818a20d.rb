class Phrase

  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    map_array_of_words.inject({}) do |hash, word|
      hash.merge(word) { |key, old, new| old + new }
    end
  end

  def map_array_of_words
    phrase_as_stripped_array.map { |word| { word => 1 } }
  end

  def phrase_as_stripped_array
    phrase.to_s.downcase.gsub(/[^a-z0-9\s]/, " ").strip.split(' ')
  end

end
