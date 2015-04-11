class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count = Hash.new {|h,k| h[k] = 0 }
    phrase.scan(/\w+/).each do |word|
      word_count[word.downcase] += 1
    end
    word_count
  end
end
