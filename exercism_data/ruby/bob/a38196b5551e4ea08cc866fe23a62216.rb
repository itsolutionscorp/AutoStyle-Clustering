class Bob

  def initialize
  end

  def hey(conversation)
    if conversation =~ /\A\s*\z/
      'Fine. Be that way!'
    elsif conversation.upcase == conversation
      'Woah, chill out!'
    elsif conversation =~ /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end

end
