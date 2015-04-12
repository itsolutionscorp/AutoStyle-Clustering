class Phrase
  def initialize text
    @text = text
  end
  def word_count
    hash = Hash.new
    words = prepare_my_words
    words.each do |word|
      hash[word] = 0 unless hash.has_key? word
      hash[word] += 1
    end
    hash
  end

  private
    def prepare_my_words
      @text.punctuation_to_space.
            whitespace_to_space.
            squeeze(' ').
            downcase.
            split(' ')
    end
end

class String
  def punctuation_to_space
    self.gsub(/\W/) {|m| if m == "'" then m else ' ' end}
  end
  def whitespace_to_space
    self.gsub(/\s/, ' ')
  end
end
