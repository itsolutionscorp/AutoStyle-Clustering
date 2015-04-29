#!/usr/bin/env ruby

# Exercism 31
# Scrabble Score

# Letter                           Value
# A, E, I, O, U, L, N, R, S, T       1
# D, G                               2
# B, C, M, P                         3
# F, H, V, W, Y                      4
# K                                  5
# J, X                               8
# Q, Z                               10

class Scrabble

  attr_reader :score

  def initialize(word)
    word == nil ? @word = '' : @word = word.delete(" \n\t").downcase

    @score_board = { ['a','e','i','o','u','l','n','r','s','t'] => 1,
                     ['d','g'] => 2,
                     ['b','c','m','p'] => 3,
                     ['f','h','v','w','y'] => 4,
                     ['k'] => 5,
                     ['j','x'] => 8,
                     ['q','z'] => 10 }
  end

  def score
    score = 0
    @word.length == 0 ? 0 : @score_board.each do |x,y|
      @word.chars.each { |z| x.include?(z) ? score += y : score += 0 }
    end
    score
  end

  # Is there a better way to accomplish this?
  def self.score(word)
    self.new(word).score
  end

end
