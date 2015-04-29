class Bob
  def hey(conversation)
    case conversation
    when ->(c){ c.strip.empty? }
      'Fine. Be that way!'
    when c.upcase
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
