require 'set'
class Anagram

  def initialize(input)
    @string = input
  end

  def match(input)
    input.each_with_object Array.new do |word, result|
      if string.downcase.chars.sort.eql? word.downcase.chars.sort\
        and not word.downcase.eql? string
        result << word
      end
    end
  end

  private
  attr_reader :string
end
