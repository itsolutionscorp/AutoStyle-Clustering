class Bob
  def hey(greeting)
    if is_nothing?(greeting)
      'Fine. Be that way!'
    elsif is_yelling?(greeting)
      'Woah, chill out!'
    elsif is_question?(greeting)
      return 'Sure.'
    else
      'Whatever.'
    end
  end

  def is_question?(greeting)
    greeting[-1] == '?'
  end

  def is_yelling?(greeting)
    greeting == greeting.upcase
  end

  def is_nothing?(greeting)
    greeting.to_s.empty?
  end
end
