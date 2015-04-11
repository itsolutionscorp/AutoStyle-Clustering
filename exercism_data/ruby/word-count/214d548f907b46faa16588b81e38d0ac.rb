# Phrase.word_count allows you to create a histogram of the number of words in a phrase
class Phrase
  attr_accessor :sentence, :words

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words_histogram = {}

    sentence_array = sentence.scan(/([0-9a-zA-Z']+)/).flatten

    sentence_array.each do |word|
      word = word.downcase
      if words_histogram.include? word
        words_histogram[word] += 1
      else
        words_histogram[word] = 1
      end
    end

    words_histogram
  end
end
