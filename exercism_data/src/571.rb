require 'pry'
class Phrase
  attr_accessor :words

  def initialize(words)
    @words = words.split(/\W/)
                  .map(&:downcase)
                  .reject(&:empty?)
  end

  def word_count
    words.group_by { |word| words.find { |el| el == word } }
         .map { |k,v| { k => v.count } }
         .inject(&:merge)
  end
end
