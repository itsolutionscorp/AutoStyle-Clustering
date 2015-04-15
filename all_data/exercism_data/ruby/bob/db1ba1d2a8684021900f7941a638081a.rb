class Bob
  def hey(what_they_said)
    case type(what_they_said.to_s)
    when :question;     sure
    when :statement;    whatever
    when :all_the_caps; whoa
    else
      fine
    end
  end

private
  def whatever
    'Whatever.'
  end

  def sure
    'Sure.'
  end

  def whoa
    'Woah, chill out!'
  end

  def fine
    'Fine. Be that way!'
  end

  def type(x)
    return :all_the_caps if all_the_caps?(x)
    return :question     if question?(x)
    return :statement    if statement?(x)
    nil
  end

  def question?(x)
    x[-1] == '?'
  end

  def statement?(x)
    x =~ /[.!]$/
  end

  def all_the_caps?(x)
    !(x =~ /[a-z]/) && (x =~ /[A-Z]/)
  end
end
