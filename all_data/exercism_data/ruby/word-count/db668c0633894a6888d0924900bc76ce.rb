class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    Hash.new(0).tap do |result|
      extract_words.each do |word|
        result[word] += 1
      end
    end
  end

  private

  def extract_words
    @words.downcase.gsub(/[^a-z|\s|0-9]/, '').split(' ')
  end
end
