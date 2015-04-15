class String
  def space_punctuation
    self.gsub /[^a-zA-Z0-9 ]/, ' '
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count normalized_phrase.split(' ')
  end

  private

    def normalized_phrase
      @phrase.space_punctuation.downcase
    end

    def count(words)
      count = Hash.new(0)

      words.each do |word|
        count[word] = count[word] + 1
      end

      count
    end

end
