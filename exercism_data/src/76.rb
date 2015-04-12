class Phrase
  attr_reader :word_count

  def initialize(phrase)
    phrase.gsub(/\W/, ' ').split.each do |word|
      word_count[word.downcase] += 1
    end
  end

  def word_count
    @word_count ||= Hash.new(0)
  end
end
