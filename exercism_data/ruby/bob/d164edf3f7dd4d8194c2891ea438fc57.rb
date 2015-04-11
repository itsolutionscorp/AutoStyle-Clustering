class Bob
  YOU_YELL = %r{^[^a-z]+$}
  YOU_ASK = %r{\?$}
  YOU_SAY_SOMETHING = %r{.+}

  def hey(statement)
    case statement
    when YOU_YELL then 'Woah, chill out!'
    when YOU_ASK then 'Sure.'
    when YOU_SAY_SOMETHING then 'Whatever.'
    else 'Fine. Be that way!'
    end
  end
end
