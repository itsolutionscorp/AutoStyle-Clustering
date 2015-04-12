class Phrase

  def initialize(phrase)
    @phrase_arr = phrase.split
    @word_hash = Hash.new
  end

  def word_count
    @phrase_arr.each do |word|
      if @word_hash.has_key?(word)
        @word_hash[word] += 1
      else
        @word_hash[word] = 1
      end
    end
    @word_hash
  end

end
