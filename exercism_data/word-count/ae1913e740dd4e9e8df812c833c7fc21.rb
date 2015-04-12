class Phrase
  def initialize text
    @text = text.downcase
  end
  
  def word_count
    text_list.uniq.each_with_object({}) do |item, hash|
      hash[item] = text_list.count(item) 
    end
  end
  
  def text_list
    @text_list ||= @text.scan(/[\w']+/)
  end

end
