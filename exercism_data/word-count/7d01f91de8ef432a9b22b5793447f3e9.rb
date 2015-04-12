class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_hash = {}
    word_array.each do |word|
      count_hash[word] = word_array.count(word)
    end
    count_hash
  end

  private

  def word_array
    @word_array ||= 
      @phrase.downcase.gsub(/[^a-z0-9\s]/i, '').split(/\s+/)
  end

end
