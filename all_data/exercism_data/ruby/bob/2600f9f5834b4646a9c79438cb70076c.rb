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
    text = greeting.gsub(/[^a-zA-Z]/, '')
    !blank?(text) && text == text.upcase
  end

  def blank?(greeting)
    greeting.strip == ''
  end
end
