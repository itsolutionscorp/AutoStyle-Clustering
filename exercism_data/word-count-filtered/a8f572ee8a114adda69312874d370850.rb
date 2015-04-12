class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase
      .split(/[^\w\']+/)
      .each_with_object(Hash.new(0)) { |word, hsh| hsh[word.downcase] += 1 }
  end
end
