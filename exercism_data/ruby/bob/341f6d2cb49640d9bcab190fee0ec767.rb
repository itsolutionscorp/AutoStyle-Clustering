class Bob
  def hey(message_to_bob)
    result = 'Whatever.'
    
    last_character = message_to_bob[-1,1]
    uppercase_pattern = Regexp.new("\\p{Lower}".force_encoding("UTF-8"))
    
    if (message_to_bob.delete(' ').eql?'')
      result = 'Fine. Be that way!'
    elsif (message_to_bob.match(uppercase_pattern).nil?)
      result = 'Woah, chill out!'
    elsif (last_character.eql?'?')
      result = "Sure."
    end
        
    return result
  end
end
