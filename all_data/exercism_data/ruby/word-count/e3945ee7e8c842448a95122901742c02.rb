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
    words.each_with_object({}) do |word,counts|
      counts[word] ||= 0
      counts[word] += 1
    end
  end
end
