require 'pry'
class Hamming

  def self.compute(first, second)
    first_strand = Strand.new(first)
    second_strand = Strand.new(second)
    first_strand.compare_to(second_strand.characters)
  end

end

class Strand

  attr_accessor :chain

  def initialize(string)
    self.chain = string
  end

  def characters
    chain.split(//)
  end

  def compare_to(array_of_chars)
    if characters.length > array_of_chars.length
      counter = -1
      characters.take(array_of_chars.length).delete_if do |char|
        counter += 1
        char == array_of_chars[counter]
      end.length
    else
      counter = -1
      characters.delete_if do |char|
        counter += 1
        char == array_of_chars[counter]
      end.length
    end
  end

end
