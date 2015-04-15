class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def words
    @phrase.split(/\W+/)
  end

  def word_count
    word_count = Hash.new
    words.uniq.each do |word|
      word_count[word] = words.count("#{word}")
    end

    word_count
  end

end
