class Anagram
  def initialize(input)
    @word=input.downcase
  end
  def match(input_array)
    input_array.delete_if{ |a| a.downcase.eql?(@word) or a.downcase.chars.sort.join != @word.chars.sort.join } 
  end
end

#test= Anagram.new('master')
#test.match(['stream', 'pigeon', 'maters'])
