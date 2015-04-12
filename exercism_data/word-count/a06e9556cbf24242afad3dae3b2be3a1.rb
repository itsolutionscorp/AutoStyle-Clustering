class Phrase
  def initialize(words)
    @words = words.downcase
  end

  def word_count
    {}.tap do |result|
      extract_words.each do |word|
        result[word] ||= 0
        result[word] += 1
      end
    end
  end

  def extract_words
    @words.gsub(/[^a-z|\s|0-9]/, '').split(' ')
  end
end
