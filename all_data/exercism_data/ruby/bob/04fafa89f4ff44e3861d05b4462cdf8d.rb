class Bob
  def hey
    case text.strip!
    when nil
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
