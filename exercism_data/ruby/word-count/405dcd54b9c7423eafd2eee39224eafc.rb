class Words
  attr_accessor :phrase

  def initialize(phrase)
    cleaned = input_cleaner(phrase)
    @phrase = cleaned.downcase.split(" ")
  end

  def count 
    word_hash = Hash.new(0)
    phrase.each do |word|
      word_hash[word] += 1
    end
    word_hash
  end

  private

  def input_cleaner(words)
    words.gsub(/\W/,' ')
  end

end
