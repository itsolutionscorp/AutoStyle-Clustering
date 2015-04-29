class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = normalize_phrase(phrase)
  end

  def word_count
    WordCounter.new(phrase).result
  end

  private

  def normalize_phrase(original_phrase)
    original_phrase.split(/[ ,:!@#\$%\^&*]/).map(&:downcase).reject{|p| p.empty?}
  end
end

class WordCounter
  attr_accessor :words, :results_hash

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
