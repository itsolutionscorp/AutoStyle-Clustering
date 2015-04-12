class Phrase

  attr_reader :phrase

  def initialize(phrase)
     @phrase =  phrase
 end

  

  def word_count
    h = Hash.new(0)
    phrase = @phrase.downcase.scan(/(\w+)/)
    phrase.each { |e| h[e] += 1 }
    h.map { |(k), v| [k, v] }.to_h
  end
end
