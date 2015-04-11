class Bob

  def hey(phrase)
    retort(phrase.to_s.strip)
  end

  def retort(phrase)
    case
    when phrase == phrase.upcase &&
              phrase =~ /[A-Z]/              then 'Whoa, chill out!'
    when phrase.end_with?('?')          then 'Sure.'
    when phrase.empty?                     then 'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
