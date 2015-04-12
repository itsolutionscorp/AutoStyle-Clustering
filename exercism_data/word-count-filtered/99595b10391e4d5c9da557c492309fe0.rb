class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/\W+/)
    result = Hash.new(0)
    words.each do |w|
      result[w] += 1
    end

    result
  end
end
