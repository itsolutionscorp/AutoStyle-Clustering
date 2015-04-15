class Phrase
  attr_accessor :phrase

  PUNCTUATION = '.:!@#$%^&*()'

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_hash = {}
    self.word_list.each do |word|
      unless word == ""
        word = word.downcase
        word_hash.include?(word) ? word_hash[word] += 1 : word_hash[word] = 1
      end
    end
    return word_hash
  end

  def word_list
    phrase = @phrase.gsub(",", " ")
    phrase.split.each do |word|
      remove_punctuation(word)
    end
  end

  def remove_punctuation(string)
    PUNCTUATION.each_char { |char| string.gsub!(char, '')  }
    return string
  end
end
