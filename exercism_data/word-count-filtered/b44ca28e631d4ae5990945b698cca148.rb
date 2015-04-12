class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count = Hash.new(0)
    @phrase.gsub(/[!&@$%^:.,]/, ' ').split.each do |word|
      word_count[word.downcase] += 1
    end
    word_count
  end

end
