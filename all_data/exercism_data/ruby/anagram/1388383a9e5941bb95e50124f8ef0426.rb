class Anagram
  
  def initialize(words)
    @letter = words.downcase.chars.sort
    @input = words
  end
  
  def match(values) 
    test = [] 
    values.each do |x|
      if x.downcase.chars.sort == @letter and x.downcase != @input 
        test << x
      end
    end
    test.uniq    
  end
end
