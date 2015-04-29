class Anagram
  
  def initialize(words)
    @letter = words.downcase.chars.sort
    @inputs = words
  end
  
  def match(values) 
    values.select { |x| x.downcase.chars.sort == @letter && x.downcase != @inputs}
  end
end
