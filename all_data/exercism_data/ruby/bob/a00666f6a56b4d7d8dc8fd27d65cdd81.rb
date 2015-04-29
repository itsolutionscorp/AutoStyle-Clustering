class Bob
  def hey(greeting)
    if greetingIsNothing?(greeting)
      'Fine. Be that way!'
    elsif greetingIsYelling?(greeting)
      'Woah, chill out!'
    elsif greetingIsQuestion?(greeting)
      return 'Sure.'
    else
      'Whatever.'
    end
  end

  def greetingIsQuestion?(greeting)
    greeting[-1] == '?'
  end

  def greetingIsYelling?(greeting)
    greeting == greeting.upcase
  end

  def greetingIsNothing?(greeting)
    greeting.to_s.empty?
  end
end
