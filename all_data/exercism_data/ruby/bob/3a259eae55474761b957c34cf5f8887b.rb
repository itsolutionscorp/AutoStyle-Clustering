class Bob
  def hey ( text='' )
    case text
    when /^[\s]+$/ , ''
      return 'Fine. Be that way!'
    when /^[A-Z\W\d]+$/ 
      return 'Woah, chill out!'
    when /\?$/
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
