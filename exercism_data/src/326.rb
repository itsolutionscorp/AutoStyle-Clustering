class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_bank = Hash.new(0)

    @phrase.split(/[^\w']+/).each do |word|
      word_bank[word.downcase] += 1
    end

    word_bank
  end
end
