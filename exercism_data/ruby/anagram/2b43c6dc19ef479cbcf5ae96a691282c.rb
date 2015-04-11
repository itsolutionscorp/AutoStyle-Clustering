class Anagram
  attr_reader :input
  def initialize(something)
    @input =  something
  end

  def match(possible_matches)
    possible_matches.select do |possible_match| 
      formatted_match = format(possible_match)

      unless formatted_match == formatted_input
        anagrams.include?(formatted_match)
      end
    end
  end

  def format(text)
    text.downcase
  end
  
  def formatted_input
    format(input)
  end

  def anagrams
    @anagrams ||= input_letter_combinations.map { |perm| perm.join } 
  end

  def input_letter_combinations
    input_letters.permutation.to_a
  end

  def input_letters
    formatted_input.split('')
  end
end
