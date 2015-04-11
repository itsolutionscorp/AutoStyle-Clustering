class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    Hash.new(0).tap do |counts|
      words.each do |word|
        counts[word] += 1
      end
    end
  end

  def words
    phrase = @phrase.gsub(/[^a-zA-Z0-9']/, ' ')
                    .downcase
                    .split(' ')
  end
end
