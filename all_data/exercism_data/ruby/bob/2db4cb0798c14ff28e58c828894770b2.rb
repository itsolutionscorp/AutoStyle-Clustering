class Bob
attr_reader :input

def hey (input)
  @input = input
  if silence?
    'Fine. Be that way!'
  elsif shouting?
    'Woah, chill out!'
  elsif question?
    'Sure.'
  else
    'Whatever.'
  end
end

  def silence?
    input.strip.empty?
  end

  def shouting?
    input == input.upcase and input != input.downcase
  end

  def question?
    input.end_with?('?')
  end
end
