class Bob
  def hey(greeting)
    response(greeting)
  end

private

  def response(greeting)
    if empty?(greeting)
      reply(:empty)
    elsif yelling?(greeting)
      reply(:yelling)
    elsif question?(greeting)
      reply(:question)
    else
      reply(:other)
    end
  end

  def empty?(greeting)
    greeting.empty?
  end

  def yelling?(greeting)
    greeting.upcase == greeting
  end

  def question?(greeting)
    greeting[-1] == '?'
  end

  def reply(greeting_type)
    { empty: 'Fine. Be that way!',
      yelling: 'Woah, chill out!',
      question: 'Sure.',
      other: 'Whatever.' }[greeting_type]
  end
end
