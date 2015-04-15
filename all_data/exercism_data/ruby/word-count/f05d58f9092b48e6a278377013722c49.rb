class Phrase

  def initialize(phrase)
    words = phrase.downcase.split(/[^[:alnum:]]+/)
    @hash = words.inject({}) do |acc, w|
      acc[w] ||= 0
      acc[w] += 1
      acc
    end
  end

  def word_count
    @hash
  end

end
