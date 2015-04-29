# This interface exists to accomodate existing code, the tests
# This interface is not how I would prefer you access the scoring
# mechanism and should be considered the last resort.
# Eventually `Scrable` will become a module and serve as nothing
# more than a namespace for concepts that live within the scrable
# universe
class Scrabble
  attr_reader :game, :word
  def initialize(word)
    @game = Scrabble::Game.new
    @word = word
  end
  
  def score
    game.score_word(word)
  end

  def self.score(word)
    Scrabble::Game.new.score_word(word)
  end
end

class Scrabble::InvalidLetterTileError < StandardError; end

class Scrabble::Game
  attr_reader :scoring_data
  def initialize(scoring_data = {})
    @scoring_data = scoring_data.empty? ? default_scrabble_scoring : scoring_data
  end

  def score_word(word)
    return 0 if word.nil?
    word.downcase.chars.reduce(0) do |total, letter| 
      begin
        total += score_for_letter(letter)
      rescue Scrabble::InvalidLetterTileError
        0
      end
    end
  end

  def score_for_letter(letter)
    scoring_data.fetch(letter) do
      raise Scrabble::InvalidLetterTileError
    end
  end

  private

  def default_scrabble_scoring
    {
      "a"=>1, 
      "b"=>3, 
      "c"=>3, 
      "d"=>2, 
      "e"=>1, 
      "f"=>4, 
      "g"=>2, 
      "h"=>4, 
      "i"=>1, 
      "j"=>8, 
      "k"=>5, 
      "l"=>1, 
      "m"=>3, 
      "n"=>1, 
      "o"=>1, 
      "p"=>3, 
      "q"=>10,
      "r"=>1, 
      "s"=>1, 
      "t"=>1, 
      "u"=>1, 
      "v"=>4, 
      "w"=>4, 
      "x"=>8, 
      "y"=>4, 
      "z"=>10
    }
  end
end

# These tests live in `scrable_spec.rb` but are here for reading
# convience :)
#
#require 'minitest/spec'
#require 'minitest/autorun'
#require_relative 'scrabble'

#describe Scrabble::Game do
  #describe 'initialization' do
    #it 'can receive a hash of scoring information' do
      #subject = Scrabble::Game.new({ 'a' => 1 })
      #subject.score_for_letter('a').must_equal 1 
    #end
    #describe 'no scoring data is passed' do
      #it 'uses a default scrable scoring data set' do
        #subject = Scrabble::Game.new 
        #('a'..'z').each do |letter|
          #subject.score_for_letter(letter).must_be :>, 0
        #end
      #end
    #end
  #end

  #describe 'score_for_letter' do
    #describe 'when the letter is in the scoring hash' do
      #it 'returns the value' do
        #subject = Scrabble::Game.new({ 'a' => 1 })
        #subject.score_for_letter('a').must_equal 1 
      #end
    #end
    #describe 'when the letter is not in the scoring hash' do
      #it 'raises an InvalidLetterTileError' do
        #subject = Scrabble::Game.new({ 'a' => 1 })
        #begin
          #subject.score_for_letter('?')
        #rescue StandardError => error
          #error.class.must_equal Scrabble::InvalidLetterTileError
        #end
      #end
    #end
  #end

  #describe 'score_for_word' do
    #it 'returns a total score for the word' do
      #subject = Scrabble::Game.new
      #word = 'apple'
      #subject.score_word(word).must_equal 9
    #end
    #describe 'the word is blank' do
      #it 'returns 0' do
        #subject = Scrabble::Game.new
        #word = ''
        #subject.score_word(word).must_equal 0
      #end
    #end
  #end
#end
