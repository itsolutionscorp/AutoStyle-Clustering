class Bob
  def hey(text)
    text.strip!
    case text
    when ''
      'Fine. Be that way!'
    when text.upcase
      'Woah, chill out!'
    when /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
