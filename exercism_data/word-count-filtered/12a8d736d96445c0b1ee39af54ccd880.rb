require 'pry'

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.downcase.scan(/\b\w+\b/).inject(Hash.new(0)) { |a,b| a[b] += 1; a }
  end
end
