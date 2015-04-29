class Bob
  
  def hey message=''
    message.strip!
    case message
    when is_shout
      "Woah, chill out!"
    when is_question
      "Sure."
    when is_silence
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
  
  private
  def is_question
    ->(message) {message.end_with? '?'}
  end
  
  def is_shout
    ->(message) {message.index(/[a-z]+/i) && (message == message.upcase)}
  end
  
  def is_silence
    ->(message) {message.empty?}
  end
end
