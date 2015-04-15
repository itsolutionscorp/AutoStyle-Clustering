class Bob  
  def hey(conversation)
    case
    when conversation =~ /^\s*\Z/
      return 'Fine. Be that way!'
    when conversation !~ /[a-z]/ && conversation =~ /[A-Z]/
      return 'Woah, chill out!'
    when conversation =~ /\?\Z/
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
