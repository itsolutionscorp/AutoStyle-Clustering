class Phrase
  def initialize(string)
    @string = Phrase.remove_punctuation(string).downcase
  end

  def word_count
    @string.split
    .each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private
    def self.remove_punctuation(string)
      string.gsub(/\p{Punct}/, ' ')
    end
end
