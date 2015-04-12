class Phrase
  
  @content
  
  def initialize(content)
    content.gsub!(/[^a-zA-Z0-9 ]+/, ' ')
    @content = content.downcase
  end
  
  def word_count
    
    word_list = Hash.new()
    words = @content.split
    
    words.each { |word|
      word_list.has_key?(word) ? word_list[word] = word_list[word] + 1 : word_list[word] = 1
    }
    
    word_list
    
  end
  
end
