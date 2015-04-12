class Phrase
  def initialize(text)
    @text = text
  end
  def word_count
    hash = Hash.new(0)
    words = prepare_my_words
    words.each {|word| hash[word] += 1}
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
