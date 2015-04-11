class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count_hash = Hash.new(0)
    process_phrase.each{|word| 
      word_count_hash[word.downcase] += 1
    }
    word_count_hash
  end

  private

  def process_phrase
    @phrase.gsub(/[^a-zA-Z0-9, ]+/, '').split(/[\s,]+/)
  end 
end
