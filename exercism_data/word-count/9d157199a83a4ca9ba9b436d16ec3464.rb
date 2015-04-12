require 'strscan'

class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    init_word_count
    normalized_words.each do |word|  
      update_count(word)
    end
    @word_count
  end

  private
  def words
    @input.split(/\W+/)
  end

  def normalized_words
    words.map(&:downcase)
  end

  def update_count(word)
    @word_count[word] += 1
  end

  def init_word_count
    @word_count = Hash.new { |hash,key|  hash[key] = 0 }
  end
end
