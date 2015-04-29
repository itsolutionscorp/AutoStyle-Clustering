class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_hash
  end

  private

  def count_hash
    @count_hash ||= Hash[word_array.collect { |w| [w, word_array.count(w)] }]
  end

  def word_array
    @word_array ||= 
      @phrase.downcase.gsub(/[^a-z0-9\s]/i, '').split(/\s+/)
  end

end
