class Phrase
  def initialize (input)
    
    input.downcase!
    @words = input.split (/[^\w']+/)  
    @words.each{|word| word.scan(/./){|char| 
        char.delete unless char =~ /[\w']/}}

    @h = Hash.new(0)
    @words.each {|word| @h[word] += 1}
  end
  
  def word_count
    return @h
  end
end
