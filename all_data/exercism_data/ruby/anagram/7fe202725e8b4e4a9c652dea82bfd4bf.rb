require "pry"
class Anagram
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def match(data = [])
  array_of_letters = data.map do |word|
              word.split("")
            end

   sorted_letters = array_of_letters.map do |letter|
                      letter.sort
                    end
   whyyyyy = sorted_letters.select {|a| a == input.split("").sort }
   whyyyyy.map {|a| a.join}
  end

end
