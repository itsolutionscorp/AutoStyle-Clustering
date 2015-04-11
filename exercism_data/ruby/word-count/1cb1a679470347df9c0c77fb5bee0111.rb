require 'shellwords'

class Words
  attr_accessor :words

  def initialize(words)
    self.words = words
  end

  def count
    result = Hash.new {|hash, key| hash[key] = 0}
    normalized_word_list.inject result do |result, word|
      result[word] = result[word].succ
      result
    end
  end

  private

  def word_list
    Shellwords.split words
  end

  def normalized_word_list
    word_list.map {|word| word.gsub(/\W/, '').downcase }.reject {|word| word.empty? }
  end
end
