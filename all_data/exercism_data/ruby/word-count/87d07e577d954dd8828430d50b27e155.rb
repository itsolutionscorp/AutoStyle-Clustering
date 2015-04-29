class Phrase
  def initialize message
    @message = message.downcase.gsub(/[^0-9A-Za-z]/,' ')
  end

  def word_count
    @count = {}
    message_array = @message.split
    message_array.each do |word|
      if @count[word]
        @count[word] += 1
      else
        @count[word] = 1
      end 
    end
    @count
  end

end
