require 'contracts'

class Scrabble
  include Contracts

  Contract Maybe[String] => Any
  def initialize(input="")
    @word = if input.nil?
              ""
            else
              input.strip.downcase
            end
  end

  Contract nil => Num
  def score
    TILE_VALUES.reduce(0) do |sum, (letterset, score)|
      sum += points_for(letterset, score)
    end
  end

  Contract String => Num
  def self.score(input)
    new(input).score
  end

  private

  TILE_VALUES = {
    /[aeioulnrst]/ => 1,
    /[dg]/ => 2,
    /[bcmp]/ => 3,
    /[fhvwy]/ => 4,
    /[k]/ => 5,
    /[jx]/ => 8,
    /[qz]/ => 10
  }
  private_constant :TILE_VALUES

  attr_reader :word

  Contract Regexp, Num => Num
  def points_for(letters, score)
    word.scan(letters).count * score
  end
end
