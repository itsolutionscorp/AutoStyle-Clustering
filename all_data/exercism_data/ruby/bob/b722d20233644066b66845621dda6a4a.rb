class Bob
  def hey ( text='' )
    case text
    when /^[\s]+$/ , ''
      return 'Fine. Be that way!'
    when text.upcase
      return 'Woah, chill out!'
    when /\?$/
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
