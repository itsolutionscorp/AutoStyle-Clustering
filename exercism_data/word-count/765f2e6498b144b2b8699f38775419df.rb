class Phrase
  attr_accessor :word_count

  def initialize(sentence)
    words = sentence.downcase.scan(/[\w\']+/)
    counts = Hash.new { |h,k| h[k] = 0 }
    words.each { |word| counts[word] += 1 }
    @word_count = counts
  end

end
