class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    self.phrase = phrase
  end

  def word_count
    @word_count ||= begin
      count(split_to_words(phrase))
    end
  end

  private
    def split_to_words(phrase)
      phrase.split(/[^[[:alnum:]]]+/).compact
    end

    def count(words)
      count_hash = Hash.new(0)
      words.each do |word|
        count_hash[word.downcase] += 1
      end
      count_hash
    end
end
