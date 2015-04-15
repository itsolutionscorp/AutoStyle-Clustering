#!/usr/bin/env ruby

VALUES = {"AEIOULNRST" => 1, "DG" => 2, "BCMP" => 3,
          "FHVWY" => 4, "K" => 5, "JX" => 8, "QZ" => 10}

class Scrabble
    def initialize(word)
        @word = word
    end
    
    def score
        Scrabble.score @word
    end
    
    def self.score(word)
        return 0 if word.nil?
        counter = 0
        word.upcase.chars.each do |letter|
            VALUES.keys.each do |letters|
                counter += VALUES[letters] if letters.chars.member? letter
            end
        end
        counter
    end
end
