class Phrase
  attr_reader :words
  def initialize(input)

    @words = input.downcase.scan(/\w+/)
  end

  def word_count
    h = {}
    words.each do |word|
      if h[word]
        h[word] += 1
      else
        h[word] = 1
      end
    end
    return h
  end
end
