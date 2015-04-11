class Phrase
  attr_reader :body

  #some extras in here for fun
  SPECIAL_CHARS = /[ ,.?:!@#\$%\^&*]/

  def initialize(body)
    @body = normalized_body(body)
  end

  def word_count
    WordCounter.new(body).result
  end

  private

  def normalized_body(original_body)
    original_body.split(SPECIAL_CHARS).reject(&:empty?).map(&:downcase)
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
