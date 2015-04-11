class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase = @phrase.gsub(/[^a-zA-Z0-9']/, ' ').downcase
    words = phrase.split(' ')
    Hash.new(0).tap do |counts|
      words.each do |word|
        counts[word] += 1
      end
    end
  end
end
