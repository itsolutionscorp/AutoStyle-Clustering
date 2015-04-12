class Phrase
  def initialize(str)
    @words = str.downcase.gsub(',', ' ').gsub(/[^a-zA-Z0-9_' \t\r\n\f]/, '').split(' ')
  end
  
  def word_count
    Hash[@words.group_by {|x| x}.map {|k,v| [k,v.count]}]
  end
end
