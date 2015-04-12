class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s.downcase.gsub(/\W/, " ")
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, collector|
      collector[word] += 1
    end
  end

  def words
    phrase.split
  end
end
