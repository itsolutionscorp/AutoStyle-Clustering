class Phrase
  def initialize(phrase)
    @word_count = count(normalize(phrase).split)
  end

  attr_reader :word_count

  private
  def normalize(phrase)
    phrase.downcase.gsub(/[^\w']/, ' ')
  end

  def count(words)
    words.reduce({}) do |counts,word|
      word = word.downcase
      counts[word] ||= 0
      counts[word] += 1
      counts
    end
  end
end
