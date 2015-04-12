class Phrase

  attr_accessor :phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counts = {}
    phrase.gsub(/[^[:alnum:]']/, " ").split.each do |i|
      word = i.downcase
      counts[word].nil? ? counts[word] = 1 : counts[word] += 1
    end
    counts
  end
end
