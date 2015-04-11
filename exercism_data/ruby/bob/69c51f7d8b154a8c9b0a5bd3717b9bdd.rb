class Bob
  def hey(conversation)
    case conversation
    when ->(c){ c.strip.empty? }
      'Fine. Be that way!'
    when ->(c){ c.gsub(/\W/i, '')[/\A[^a-z]+\z/] }
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
