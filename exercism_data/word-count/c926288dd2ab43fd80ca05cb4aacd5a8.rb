class Phrase
  def initialize(phrase = nil)
    @phrase = phrase.to_s
  end
  
  def word_count
    @phrase.strip.downcase.split(/\W+/).inject(Hash.new(0)) { |h,w| h[w] += 1; h }
  end
end
