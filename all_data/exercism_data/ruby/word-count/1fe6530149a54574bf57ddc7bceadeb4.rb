class Phrase
  attr_reader :word_count

  def initialize string
    @string = string
    detect_words!
    count_words
  end

  def remove_spaces!
    @string.gsub! /\s/, '_'
  end

  def remove_punctuation!
    @string.gsub! /\W/, ''
  end

  def detect_words!
    remove_spaces!
    remove_punctuation!
    @words = @string.split('_')
  end

  def count_words
    @word_count = @words.inject({}) do |hash, word|
      unless word.empty?
        hash[word.downcase] ||= 0
        hash[word.downcase] += 1
      end

      hash
    end
  end
end
