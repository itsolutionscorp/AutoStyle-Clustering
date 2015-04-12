class Phrase
  def initialize(phrase)
    @phrase = normalize phrase
  end

  def word_count
    count(@phrase)
  end

  private

  def normalize(phrase)
    phrase.gsub(/\W/, " ").downcase.split
  end

  def count(words)
    word_count   = {}
    words.each do |word|
      word_count[word] = (word_count.include? word) ? increment(word_count[word]) : 1
    end
    word_count
  end

  def increment(int)
    int += 1
  end
end
