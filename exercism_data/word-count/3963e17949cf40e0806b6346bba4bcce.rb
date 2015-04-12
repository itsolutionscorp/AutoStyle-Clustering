class Phrase
  def initialize(sentence)
    # sanitize input for our needs
    @words = remove_punctuation(sentence).split(" ")
  end

  def word_count
    count_words
  end

  private
  
  def count_words
    hash = Hash.new
    @words.each do |word|
      if hash[word]
        hash[word] += 1
      else
        hash[word] = 1
      end
    end
    hash
  end

  def remove_punctuation(sentence)
    sentence.downcase.gsub(/[^a-z0-9\s]/, ' ')
  end
end
