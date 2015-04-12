class Phrase

  def initialize(phrase_string)
    @phrase_string = phrase_string
  end

  def word_count
    if @word_count.nil?
      word_pattern = /[\w']+/
      @word_count = Hash.new{|hash, key| hash[key] = 0 }
      @phrase_string.scan(word_pattern) { |word|
        @word_count[word.downcase] += 1
      }
    end
    @word_count
  end

end
