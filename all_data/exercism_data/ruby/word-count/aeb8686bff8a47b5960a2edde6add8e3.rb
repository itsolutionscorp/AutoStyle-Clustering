class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counter = {}

    @phrase
      .split(/[^a-z0-9\']/i)
      .each { |word|
        word = word.strip.downcase
        unless(word.empty?)
          (counter.has_key?(word)) ? counter[word] += 1 : counter[word] = 1
        end
      }

    counter
  end

end
