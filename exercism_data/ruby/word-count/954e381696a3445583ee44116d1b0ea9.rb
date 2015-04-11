class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count()
    words = @phrase.downcase.split(/[^\w]+/)
    freqs = Hash.new(0)
    words.each {|word| freqs[word] += 1}
    freqs
  end
end
