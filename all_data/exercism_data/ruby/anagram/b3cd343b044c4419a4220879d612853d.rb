class Anagram
  
  def initialize(words)
    @letter = words.split(//).permutation.map{|n| n.join}
    @input = words
  end
  
  def match(values) 
    test = []    
    values.each do |x|
      @letter.each do |y|
        if x.downcase == y.downcase and y != @input 
          test << x
        end
      end
    end
    test.uniq
  end
end
