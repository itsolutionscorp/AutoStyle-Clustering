class Bob
  def hey(input)
    whitespace =  !!input.match(/\A(\s*)\z/)
    all_caps   =  !!input.match(/\A([^[a-z]]+)\z/) && !!input.match(/[A-Z]+/)
    a_question =  !!input.match(/.+\?\z/)

    case
    when whitespace
      'Fine. Be that way!'
    when all_caps
      'Woah, chill out!'
    when a_question
      'Sure.'
    else
      'Whatever.'
    end
  end
end
