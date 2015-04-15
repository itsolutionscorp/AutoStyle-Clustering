class Phrase
  def initialize word
    @word = word
  end

  def word_count
    normalize(@word).scan(/['\w]+/).inject(Hash.new(0)) do |wordsMap, word|
      wordsMap[word] += 1
      wordsMap
    end
  end

  private

  def normalize word
    word.downcase.strip.gsub Regexp.new('[^a-z0-9,\' ]', Regexp::IGNORECASE), ''
  end
end
