class Bob
  def hey(drivel)
    case
    when taciturn?(drivel)
      'Fine. Be that way!'
    when forceful?(drivel)
      'Woah, chill out!'
    when curious?(drivel)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def taciturn?(s)
    s.strip.empty?
  end

  def curious?(s)
    s.end_with?('?')
  end

  def forceful?(s)
    s =~ /[A-Z]/ && s.upcase == s
  end
end
