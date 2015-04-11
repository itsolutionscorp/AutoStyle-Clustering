class Anagram

  attr_reader :word, :word_letters

  def initialize(input)
    @word = input.downcase
    @word_letters = deconstruct(@word)
  end

  def match(words)
    words.select do |w|
      deconstruct(w) == word_letters unless w.downcase == word
    end
  end

  def deconstruct(word)
    word.chars.sort
  end

end







# class Anagram

#   attr_accessor :word

#   def initialize(word)
#     @word = word.downcase
#   end

#   def match(words)
#     words.select { |w| permutations.include? w.downcase }
#   end

#   def permutations
#     word_letters.permutation.to_a.collect { |letters| letters.join }
#   end

#   def word_letters
#     word.split('')
#   end

# end
