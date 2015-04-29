class Phrase
  
  def initialize(words)
    raise "must be a non empty String!" if not_valid?(words)
    @words = words
  end
  
  def word_count
    @words.split(/\s|\W/).map(&:downcase).reject(&:empty?).inject(Hash.new(0)) { |result, word| result[word] +=1; result }
  end
  
  def not_valid?(words)
    words.empty? || !words.is_a?(String)
  end
  
end
