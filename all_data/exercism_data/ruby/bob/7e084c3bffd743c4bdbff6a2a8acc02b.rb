class Bob
  def hey(message)
    message ||= ''
    case message
    when /\A\z/
      'Fine. Be that way.'
    when /\?\z/
      'Sure.'
    when /\A([A-Z0-9_]|\W)+\z/
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
