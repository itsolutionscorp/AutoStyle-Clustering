class Bob
  def hey(greeting)
    case
    when shouting?(greeting) then 'Woah, chill out!'
    when blank?(greeting)    then 'Fine. Be that way!'
    when question?(greeting) then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def question?(greeting)
    greeting.end_with?('?')
  end

  def shouting?(greeting)
    greeting == greeting.upcase && greeting =~ /[A-Z]/
  end

  def blank?(greeting)
    greeting.strip == ''
  end
end
