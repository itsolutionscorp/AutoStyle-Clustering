class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    histogram = Hash.new(0)
    inputs.each do |input|
      histogram[input] += 1
    end
    return histogram
  end

  def inputs
    @phrase.scan(/\w+/)
  end

end
