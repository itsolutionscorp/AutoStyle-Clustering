class Phrase

  def initialize(phrase)
    words = phrase.downcase.split(/[^[:alnum:]]+/)
    @hash = words.inject(Hash.new(0)) do |acc, w|
      acc[w] += 1
      acc
    end
  end

  def word_count
    @hash
  end

end
