class Bob
  REACTIONS = {
    silence: 'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    question: 'Sure.',
    default: 'Whatever.'
  }

  def hey(input)
    input ||= ''
    type = input_type(input)
    REACTIONS[type]
  end

  def input_type(input)
    return :silence if input.strip == ''
    return :shouting if input.upcase == input
    return :question if input.end_with? '?'
    :default
  end
end
