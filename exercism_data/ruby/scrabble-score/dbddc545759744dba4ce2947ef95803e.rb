require 'pry'
class Scrabble
  SCORES = {}
  %w{A E I O U L N R S T}.each do |letter|
    SCORES[letter] = 1
  end
  %w{D G}.each {|letter| SCORES[letter] = 2}
  %w{B C M P}.each {|letter| SCORES[letter] = 3}
  %w{F H V W Y}.each {|letter| SCORES[letter] = 4}
  SCORES['K'] = 5
  %w{J X}.each {|letter| SCORES[letter] = 8}
  %w{Q Z}.each {|letter| SCORES[letter] = 10}

  def self.score(word)
    self.new(word).score
  end

  def initialize(word)
    @word = word ? word.upcase.strip : ''
  end

  def score
    @word.each_char.map{|char| SCORES[char]}.inject(:+) || 0
  end
end
