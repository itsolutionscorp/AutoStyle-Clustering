class Bob
  def hey(greeting)
    if shouting?(greeting)
      'Woah, chill out!'
    elsif blank?(greeting)
      'Fine. Be that way!'
    elsif question?(greeting)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def question?(greeting)
    greeting[-1] == '?'
  end

  def shouting?(greeting)
    text = greeting.gsub(/[^a-zA-Z]/, '')
    !blank?(text) && text == text.upcase
  end

  def blank?(greeting)
    greeting.strip == ''
  end
end
