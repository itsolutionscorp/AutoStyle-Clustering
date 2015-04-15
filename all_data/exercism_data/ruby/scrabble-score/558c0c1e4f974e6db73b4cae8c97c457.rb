class Scrabble
  attr_reader :word

  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    @word = word
  end

  def score
    scrabble_score(@word)
  end
end

def scrabble_score(word)
  valid_word?(word) ? calculate_sum_score(word) : 0
end

def calculate_sum_score(word)
  letters = word.upcase.chars
  scores_by_letters(letters)
end

def scores_by_letters(letters)
  letters.reduce(0) do |sum_score, letter|
    sum_score + value_by_letter(letter)
  end
end

def value_by_letter(letter)
  case
  when 'AEIOULNRST'.include?(letter) then 1
  when 'DG'.include?(letter) then 2
  when 'BCMP'.include?(letter) then 3
  when 'FHVWY'.include?(letter) then 4
  when 'K'.include?(letter) then 5
  when 'JX'.include?(letter) then 8
  when 'QZ'.include?(letter) then 10
  else 0
  end
end

def valid_word?(word)
  word ? !word.strip.empty? : false
end
# go to clojure with his clean functions
