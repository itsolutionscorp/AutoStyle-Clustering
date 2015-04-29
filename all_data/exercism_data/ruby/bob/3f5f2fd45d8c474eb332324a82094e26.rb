class Bob
  CAPS = "A-Z"
  LOWERCASE = "a-z"
   
  SPACE = "\\s"
  NON_WORD_CHAR = "\\W"
  DIGITS = "\\d"

  SHOUT = "#{CAPS}#{SPACE}#{NON_WORD_CHAR}#{DIGITS}"
  ZERO_OR_MANY_SPACES = "#{SPACE}*"
  LETTERS = "#{CAPS}#{LOWERCASE}" 

  def hey(greeting)
    if silent?(greeting)
      "Fine. Be that way!"
    elsif shout?(greeting)
      "Woah, chill out!" 
    elsif question?(greeting)
      "Sure."
    else 
      "Whatever."
    end
  end

  def silent?(greeting)
    greeting.nil? || greeting.strip =~ /^#{ZERO_OR_MANY_SPACES}$/
  end
   
  def shout?(greeting)
    greeting =~ /^[#{SHOUT}]+$/
  end

  def question?(greeting)
    greeting =~ /[#{LETTERS}]+\?/
  end

end
