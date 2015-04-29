class Bob
  def hey(listen)
    respond(listen.delete ' ')
  end

  def respond(heard)
    case
    when nothing?(heard)
      'Fine. Be that way!'
    when yelling?(heard)
      'Woah, chill out!'
    when question?(heard)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def nothing?(heard)
    heard.empty?
  end

  def yelling?(heard)
    heard.upcase == heard && heard.match(/[A-Z]/)
  end

  def question?(heard)
    heard.end_with?('?') unless yelling?(heard)
  end
end
