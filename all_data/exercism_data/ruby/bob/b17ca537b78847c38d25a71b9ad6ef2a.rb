class Bob
  def hey(greeting)
    case
    when nothing?(greeting)
      'Fine. Be that way!'
    when yell?(greeting)
      'Whoa, chill out!'
    when question?(greeting)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def nothing?(greeting)
    greeting.strip.empty?
  end

  def yell?(greeting)
    !greeting.match(/[a-z]/) && greeting.match(/[A-Z]/)
  end

  def question?(greeting)
    greeting.chars.last == '?'
  end
end
