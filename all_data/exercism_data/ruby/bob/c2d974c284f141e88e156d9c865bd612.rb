class Bob
  def hey(words)
    if nothing?(words)
      'Fine. Be that way!'
    elsif yelling?(words)
      'Woah, chill out!'
    elsif asking?(words)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def nothing?(words)
    words.strip.length == 0
  end

  def asking?(words)
    words[-1] == '?'
  end

  def yelling?(words)
    tmp = words.gsub(/\W|[0-9]/, '')
    tmp.length > 0 && tmp == tmp.match(/[A-Z]+/).to_s
  end
end
