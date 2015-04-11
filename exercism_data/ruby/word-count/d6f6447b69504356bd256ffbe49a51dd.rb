require 'pry'

class Phrase
  def initialize(message)
    @message = message
  end

  def word_count
    cleaned_message = scan(@message)
    hashify(cleaned_message)
  end

  private

  def hashify(message)
    hash = Hash.new(0)
    message.each_with_object(hash) {|word| hash[word] += 1 }
  end

  def scan(message)
    message.downcase.scan(/[\b\w]+/)
  end

end
