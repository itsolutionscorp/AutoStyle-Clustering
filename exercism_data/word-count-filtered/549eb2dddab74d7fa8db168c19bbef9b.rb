class Phrase 
  
  def initialize(sentence)
    @count = Hash.new 0
    sentence.downcase.scan(/[\w']+/) do |n|
      @count[n]+=1
    end
  end
  
  def word_count
    @count
  end
end
