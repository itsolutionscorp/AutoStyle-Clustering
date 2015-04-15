class Phrase

  attr_accessor :phrase

  def initialize (phrase)
    @phrase = phrase.dup
  end

  def word_count
    @word_counts ||= begin
      phrase.downcase!
      phrase.gsub!(/,/, ' ')
      phrase.gsub!(/[^0-9a-z' ]/i, '')
      words=phrase.split(' ')
      word_counts=Hash.new 0
      words.each { |word| word_counts[word] += 1 }
      word_counts
    end
  end

end
