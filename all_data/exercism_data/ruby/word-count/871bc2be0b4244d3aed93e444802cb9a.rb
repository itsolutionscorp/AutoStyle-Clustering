class Phrase

  def initialize phrase
    @frequency = Hash.new{0}
    histogram(normalize(phrase))
  end

  def word_count
    @frequency
  end

  def normalize phrase
    phrase.gsub(/[^[:alnum:](\w'\w)]/,' ').downcase
  end

  def histogram phrase
    phrase.split.each {|word| @frequency[word] = @frequency[word] + 1 }
  end

end
