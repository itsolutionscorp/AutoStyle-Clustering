class Words
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = normalized_string(phrase)
  end

  def count 
    word_hash = Hash.new(0)
    phrase.each do |word|
      word_hash[word] += 1
    end
    word_hash
  end

  private

  def normalized_string(words)
    words.downcase.gsub(/\W/,' ').split(" ")
  end
end
