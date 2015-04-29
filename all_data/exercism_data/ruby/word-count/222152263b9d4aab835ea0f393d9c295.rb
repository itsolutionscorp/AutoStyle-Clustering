class Phrase
  def self.word_count(phrase)
    Hash.new(0).tap do |count|
      phrase.scan(/[[:word:]']+/) { |word| count[word.downcase] += 1 }
    end
  end

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= self.class.word_count(@phrase)
  end
end
