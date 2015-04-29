class Bob
  def hey(text)
    question_regex = /\?$/
    case text.strip
    when ''
      'Fine. Be that way!'
    when text.upcase
      'Woah, chill out!'
    when question_regex
      'Sure.'
    else
      'Whatever.'
    end
  end
end
