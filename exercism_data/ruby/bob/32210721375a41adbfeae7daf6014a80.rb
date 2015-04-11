class Bob

  def hey(input)
    case
    when silent?(input)
      'Fine. Be that way!'
    when yelling?(input)
      'Woah, chill out!'
    when asking_a_question?(input)
      return 'Sure.'
    else
      'Whatever.'
    end
  end
end

def silent?(input)
  input.size == 0 || !input.match(/[^\s]+/)
end

def yelling?(input)
  input.match(/[A-Z]+/) && !input.match(/[a-z]+/)
end

def asking_a_question?(input)
  input.end_with?('?')
end
