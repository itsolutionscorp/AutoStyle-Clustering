class Bob
  def hey(message)
    yell     = lambda { |m| m.upcase == m && m.downcase != m }
    question = lambda { |m| m =~ /\?\Z/ }
    silence  = lambda { |m| m =~ /\A\s*\Z/ }

    case message
    when yell
      'Whoa, chill out!'
    when question
      'Sure.'
    when silence
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
