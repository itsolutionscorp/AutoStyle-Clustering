class Bob
  def hey(msg)
    case msg
    # Case silence
    when /\A[\s]*\z/
      'Fine. Be that way!'
    # Case only number and not question
    when /\A[\d\W]+[^\?]\z/
      'Whatever.'
    # Case only number and a qestion
    when /\A[\d\W]+\?\z/
      'Sure.'
    # Case shouting
    when /\A[A-Z\W\d]+\z/
      'Woah, chill out!'
    when /\w+\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
