class Phrase
  
  def initialize(string)
    raise "must be a non empty String!" if invalid?(string)
    @words = sanitize string
  end
  
  def word_count
    @words.inject(Hash.new(0)) {|result, word| result[word] +=1; result}
  end
  
  private
  
  def sanitize(string)
    string.split(/\s|\W/).reject(&:empty?).map(&:downcase)
  end
  
  def invalid?(string)
    string.empty? || !string.is_a?(String)
  end
  
end
