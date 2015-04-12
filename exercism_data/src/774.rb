=begin
  File: phrase.rb
  Author: sherinom
=end

class Phrase

  def initialize (string)
    @phrase = string
  end

  def word_count
    count = Hash.new 0
    #@phrase.downcase.tr('.,:;', ' ').split.each do |word|
    @phrase.downcase.tr('^-\'a-z0-9', ' ').split.each do |word|
      count[word] += 1
    end
    count
  end

end
