class Bob
  def hey(what)
    case
    when what.strip.empty?
      'Fine. Be that way!'
    when what == what.upcase
      'Woah, chill out!'
    when what.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
