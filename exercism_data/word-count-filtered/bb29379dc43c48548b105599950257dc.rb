class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase
      .scan(/([\w']+)/)
      .flatten
      .each_with_object(Hash.new(0)) {|word, h| h[word.downcase] += 1}
  end
end
