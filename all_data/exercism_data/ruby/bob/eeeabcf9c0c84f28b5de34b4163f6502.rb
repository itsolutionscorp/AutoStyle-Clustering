class Bob
  def hey(message)
    case message
      when message.strip.empty?
        'Fine. Be that way!'
      when /\A([^a-z]+[A-Z]+[^a-z]+)*\Z/ # Yelling (sans question)
        'Woah, chill out!'
      when /\A.*?\?$\Z/ # Any Question
        'Sure.'
      else
        'Whatever.'
    end
  end
end
