class Phrase
  attr_reader :sentence
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    require 'pry'
    word_count = Hash.new(0)
    if sentence.include?(',')
      new_sentence = []
      sentence_split = sentence.split(',')
      sentence_split.each do |sentence|
        new_sentence << sentence.split(' ')
      end
      new_sentence
    else
      new_sentence = sentence.split(' ')

    end
      new_sentence.flatten.each do |word|
      matched_word = word.match(/[a-zA-Z0-9]/)
      matched_word ? formatted_word = matched_word.string.gsub(/[^a-zA-Z0-9]/, '') : next
      word_count[formatted_word.downcase] += 1
    end
    word_count
  end
end
