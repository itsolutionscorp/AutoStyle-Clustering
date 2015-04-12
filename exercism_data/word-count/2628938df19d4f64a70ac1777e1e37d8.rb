class Phrase

  def initialize(phrase)
    @phrase = normalized phrase
  end

  def word_count
    result = Hash.new { |hash, key| hash[key] = 0 }
    @phrase.split(" ").each { |word| result[word] +=1 }
    result
  end

  private

  def normalized phrase
    phrase.gsub(/[^a-z0-9'\s]/i, ' ').downcase
  end

end
