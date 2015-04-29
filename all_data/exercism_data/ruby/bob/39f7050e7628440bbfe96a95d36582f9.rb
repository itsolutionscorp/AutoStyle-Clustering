class Bob
  def hey(input)
    case 
    when silence(input) 
     'Fine. Be that way!'
    when prolonged_silence(input)
     'Fine. Be that way!'
    when shouting(input)
     'Woah, chill out!'
    when contains_question_mark(input)
     'Whatever.'
    when on_multiple_lines(input)
     'Whatever.'
    when asks_a_question(input)
     'Sure.'
    else 'Whatever.'

    end
  end

  def silence(input)
    input == ''  
  end

  def shouting(input)
    input == input.upcase 
  end

  def prolonged_silence(input)
    input.chars.all? { |e|  e == ' '}
  end

  def contains_question_mark(input)
    input.include?("?") && !input.end_with?('?')
  end

  def on_multiple_lines(input)
    input.lines.count > 1
  end

  def asks_a_question(input)
    input.end_with?('?')
  end

end
