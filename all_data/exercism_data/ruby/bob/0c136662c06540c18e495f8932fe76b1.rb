class Bob
  def hey(listen)
    response = 'Fine. Be that way!' if heard(listen) == 'nothing'
    response = 'Woah, chill out!'   if heard(listen) == 'yelling'
    response = 'Sure.'              if heard(listen) == 'question'
    response = 'Whatever.'          if heard(listen) == 'blah blah'
    return response
  end

  def heard(input)
    input.delete! ' '
    case
    when input.empty?
      return 'nothing'
    when input.upcase == input && input.match(/[A-Z]/)
      return 'yelling'
    when input.end_with?('?')
      return 'question'
    else
      return 'blah blah'
    end
  end
end
