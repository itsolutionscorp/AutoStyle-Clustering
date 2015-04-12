class Phrase

  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counts = {}
    phrase.downcase.gsub(/[^[:alnum:]']/, " ").split.each do |word|
      counts[word].nil? ? counts[word] = 1 : counts[word] += 1
    end
    counts
  end
end
