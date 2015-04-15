class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    word_hash = {}
    word_into_array(@phrase).each do |word|
      count = 1
      count = word_hash["#{word}"] + 1 if word_hash.has_key?("#{word}")
      word_hash.store("#{word}", count )
    end
    word_hash
  end

  private
  def word_into_array(phrase)
    phrase.scan(/[\w']+/)
  end

end
