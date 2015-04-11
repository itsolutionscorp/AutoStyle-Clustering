class Phrase
  attr_writer :cleaner

  def initialize(sentence)
    @words = {}
    process sentence.scan(/\w+/)
  end

  def add(word)
    @words[word]  = 0 unless @words.has_key? word
    @words[word] += 1
  end

  def word_count
    @words
  end

  def cleaner
    @cleaner || WordToDowncase
  end

  private

  def process(words)
    words.each { |word| add clean word }
  end

  def clean(word)
    cleaner.clean(word)
  end
end

module WordToDowncase
  def self.clean(string)
    string.downcase
  end
end
