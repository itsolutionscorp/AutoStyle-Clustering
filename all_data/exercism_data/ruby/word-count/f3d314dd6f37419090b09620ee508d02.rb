class Phrase

  def initialize(phrase)
    @words = phrase.downcase.scan(/[\w]+/)
  end

  def word_count
    frequencies = Hash.new(0)

    @words.each do |w|
      frequencies[w] += 1
    end

    frequencies
  end
end
