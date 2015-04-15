class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s.downcase.gsub(/\W/, " ")
  end

  def word_count
    collector = Hash.new(0)
    words.each do |word|
      collector[word] += 1
    end
    collector
  end

  def words
    phrase.split
  end
end
