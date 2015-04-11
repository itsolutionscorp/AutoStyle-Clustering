class Bob
  def hey(message)
    case message
    when shouted then "Woah, chill out!"
    when question then "Sure."
    when silent then "Fine. Be that way!"
    else "Whatever."
    end
  end
  
private
  def shouted
    ->(message) { message.upcase == message && message.chars.any? { |l| l =~ /[[:alpha:]]/ } }
  end
  
  def question
    ->(message) { message.end_with? '?' }
  end
  
  def silent
    ->(message) { message.strip.empty? }
  end
end
