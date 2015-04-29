class Bob
  def hey(input)
    case
    when input.is_whitespace?
      'Fine. Be that way!'
    when input.is_all_caps?
      'Woah, chill out!'
    when input.is_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
class String
  def is_whitespace?
    !!self.match(/\A(\s*)\z/)
  end
  def is_all_caps?
    !!self.match(/\A([^[a-z]]+)\z/) && !!self.match(/[A-Z]+/)
  end
  def is_a_question?
    !!self.match(/.+\?\z/)
  end
end
