class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new(0)
    @phrase.
      downcase.
      gsub(/[^a-z0-9'\s]/i, ' ').
      split.each {|word| count[word] += 1}
    count
  end
end
