class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def find_words
    @phrase.downcase.gsub(/[^\w\s]/, '').split(' ')
  end

  def word_count
    word_count = Hash.new
    find_words.uniq.each do |word|
      word_count[word] = @phrase.downcase.scan(/\b#{word}\b/).count
    end

    word_count
  end

end
