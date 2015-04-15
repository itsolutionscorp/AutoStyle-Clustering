class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    phrase.gsub(/\W+/, " ").squeeze(" ").downcase.split("\s")
  end

  def word_count
    words.reduce(Hash.new(0)) { |count, word| count[word] += 1 ; count }
  end
end
