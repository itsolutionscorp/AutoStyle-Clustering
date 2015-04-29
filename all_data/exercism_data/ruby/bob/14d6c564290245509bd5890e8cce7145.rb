class Bob
  def hey(statement)
    silence = %r{\A\s*\z}
    shouting = statement.upcase
    questioning = %r{\A*+\?\z}

    case statement
    when silence
      'Fine. Be that way!'
    when shouting
      'Woah, chill out!'
    when questioning
      'Sure.'
    else
      'Whatever.'
    end
  end
end
