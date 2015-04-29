class Phrase
  def initialize (input)
    
    words = input.downcase.split (/[^\w']+/)  
    words.each{|word| word.scan(/./){|char| 
        char.delete unless char =~ /[\w']/}}

    @h = Hash.new(0)
    words.each {|word| @h[word] += 1}
  end
  
  def word_count
    return @h
  end
end
