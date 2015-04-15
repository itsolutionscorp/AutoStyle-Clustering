class Bob
  def hey(message)
    phrase = message.strip

    case phrase
    when empty?
      'Fine. Be that way!'
    when shouting?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def question?
    -> (phrase) { phrase.end_with?('?') }
  end

  def shouting?
    -> (phrase) { !phrase[/[a-z]/] }
  end

  def empty?
    -> (phrase) { phrase.empty? }
  end
end
