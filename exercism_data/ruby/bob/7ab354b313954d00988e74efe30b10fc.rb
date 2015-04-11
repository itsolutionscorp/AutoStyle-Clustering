class Bob
  
  def hey message=''
    case message.strip
    when shout?
      "Woah, chill out!"
    when question?
      "Sure."
    when silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
  
  private
  def question?
    ->(message) {message.end_with? '?'}
  end
  
  def shout?
    ->(message) {message.index(/[a-z]+/i) && (message == message.upcase)}
  end
  
  def silence?
    ->(message) {message.empty?}
  end
end
