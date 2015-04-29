class Bob
  RESPONSES = {
    default: 'Whatever.',
    exclamation: 'Woah, chill out!',
    question: 'Sure.',
    silence: 'Fine. Be that way!'
  }

  def hey(phrase)
    phrase = phrase.chars
    mark = phrase.pop if ['!', '?', '.'].include? phrase.last
    case
    when phrase.all? { |c| c.match(/\s/) }
      RESPONSES[:silence]
    when phrase.all? { |c| c.match(/[0-9]|\s|,/) }
      mark == '?' ? RESPONSES[:question] : RESPONSES[:default]
    when phrase.all? { |c| c.match(/[A-Z]|\s|\W|[0-9]/) }
      RESPONSES[:exclamation]
    when mark == '?'
      RESPONSES[:question]
    else
      RESPONSES[:default]
    end
  end
end
