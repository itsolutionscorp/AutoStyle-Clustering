class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_count unless @word_count.nil?

    @word_count = Hash.new(0)
    @phrase
      .split(/[^\w']+/)
      .map { |word| word.downcase }
      .each { |word| @word_count[word] += 1 }
    @word_count
  end
end
