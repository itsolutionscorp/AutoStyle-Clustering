class Bob

  RESPONSES = {
    exclamation: 'Woah, chill out!',
    question: 'Sure.',
    nothing: 'Fine. Be that way!',
    default: 'Whatever.'
  }

  def hey(str)
    if str =~ /[a-w]/i && str.upcase == str
      RESPONSES[:exclamation]
    elsif str.end_with?('?')
      RESPONSES[:question]
    elsif str.strip.empty?
      RESPONSES[:nothing]
    else
      RESPONSES[:default]
    end
  end
end
