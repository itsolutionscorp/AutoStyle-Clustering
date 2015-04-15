class Bob
  def hey(telling)
    # Convert to a string and remove leading a trailing
    # white spaces
    telling = telling.to_s.strip

    #if you tell him nothing
    if telling.empty?
      'Fine. Be that way!'

    #if you yell him
    elsif telling == telling.upcase
      'Woah, chill out!'

    #if you ask him
    elsif telling.end_with?('?')
      'Sure.'

    #if you tell him some other thing
    else
      'Whatever.'
    end

  end
end
