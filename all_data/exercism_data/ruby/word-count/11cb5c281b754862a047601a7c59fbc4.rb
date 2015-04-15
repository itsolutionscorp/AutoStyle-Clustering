class Phrase
  attr_reader :phrase

  #some extras in here for fun
  SPECIAL_CHARS = /[ ,.?:!@#\$%\^&*]/

  def initialize(phrase)
    @phrase = normalize_phrase(phrase)
  end

  def word_count
    WordCounter.new(phrase).result
  end

  private

  def normalize_phrase(original_phrase)
    original_phrase.split(SPECIAL_CHARS).reject(&:empty?).map(&:downcase)
  end
end

class WordCounter
  attr_reader :words
  attr_accessor :results_hash

  def initialize(words)
    @words = words
    @results_hash = {}
  end

  def result
    build_results_hash 
    results_hash
  end

  private

  def build_results_hash
    words.map{|w| results_hash[w] = words.count(w)}
  end
end
