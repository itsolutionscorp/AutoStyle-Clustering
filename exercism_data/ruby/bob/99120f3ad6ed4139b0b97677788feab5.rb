class Bob
  def hey(string)
    question = string.to_s.strip
    case
    when question.match(/[A-Z,0-9]+(?:\s+[A-Z-]+)+\b/)
      'Woah, chill out!'
    when question.end_with?('?')
      'Sure.'
    when question.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
