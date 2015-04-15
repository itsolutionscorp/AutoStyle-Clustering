class Phrase
  def initialize(phrase)
    split_words(phrase).each do |word|
      count(word)
    end
  end

  def word_count
    @word_count ||= Hash.new(0)
  end

  private

  def split_words(phrase)
    phrase.scan(/\w+/)
  end

  def count(word)
    word_count[word.downcase] += 1
  end
end
