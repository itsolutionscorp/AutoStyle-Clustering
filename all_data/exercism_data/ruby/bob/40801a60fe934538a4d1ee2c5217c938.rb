class Bob
  def hey(phrase)
    phrase ||= ''

    shouting = ->(input) { input == input.upcase }
    question = ->(input) { input.end_with? '?' }
    empty    = ->(input) { input.strip == '' }

    case phrase
    when empty
      'Fine. Be that way!'
    when shouting
      'Woah, chill out!'
    when question
      'Sure.'
    else
      'Whatever.'
    end
  end

end
