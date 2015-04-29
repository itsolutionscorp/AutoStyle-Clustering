class Bob
  def hey(conversation)
    case conversation
    when ->(c){ c.strip.empty? }
      'Fine. Be that way!'
    when ->(c){ c.upcase == c }
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
