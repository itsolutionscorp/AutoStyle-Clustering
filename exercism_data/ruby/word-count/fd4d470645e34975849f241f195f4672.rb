class Phrase
  def initialize word
    @word = word
  end

  def word_count
    split(normalize(@word)).inject(Hash.new { |h,k| h[k] = 0 }) do |hash, word|
      hash[word] += 1
      hash
    end
  end

  private

  def normalize word
    word.downcase.strip.gsub Regexp.new('[^a-z0-9,\' ]', Regexp::IGNORECASE), ''
  end

  def split word
    word.split /[,\s]+/
  end
end
