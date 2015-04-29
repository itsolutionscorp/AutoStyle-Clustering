class Phrase
  def initialize(str)
    @text = str
  end
  
  def word_count
    Hash[words.group_by {|x| x}.map {|k,v| [k,v.count]}]
  end
  
  private 
  
    def words
      @text.downcase.gsub(',', ' ').gsub(/[^a-zA-Z0-9_' \t\r\n\f]/, '').split(' ')
    end
  
end
