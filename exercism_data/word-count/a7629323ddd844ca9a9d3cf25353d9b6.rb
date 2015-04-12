class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def words
    @phrase.split(/\W/).reject { |w| w.empty? }
  end

  def word_count
    word_count = Hash.new
    words.uniq.each do |word|
      word_count[word] = @phrase.scan(/\b#{word}\b/).count
    end

    word_count
  end

end
