class Phrase

  def initialize(message)
    @message = message.downcase.gsub(/[^0-9A-Za-z]/, ' ')
  end

  def word_count
    array = @message.split
    count = Hash.new(0)
    array.map{|word| count[word] += 1}
    count
  end

end
