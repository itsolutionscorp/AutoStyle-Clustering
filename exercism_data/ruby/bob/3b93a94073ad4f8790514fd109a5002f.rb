class Bob
  def hey(message)
    m = message.strip

    if m == ''
      return 'Fine. Be that way!'
    end

    if !m[/[a-z]/]
      return 'Woah, chill out!'
    end

    if m[-1] == '?'
      return 'Sure.'
    end

    'Whatever.'
  end
end
