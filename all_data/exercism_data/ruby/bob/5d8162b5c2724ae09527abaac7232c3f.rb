class Bob
  def hey(saying)
    if silent?(saying)
        return 'Fine. Be that way!'
    elsif shouting?(saying)
        return 'Woah, chill out!'
    elsif question?(saying)
        return 'Sure.'
    end
    'Whatever.'
  end

  def silent?(s)
    s.nil? || s.empty?
  end

  def question?(s)
    s.end_with?('?')
  end

  def shouting?(s)
    s.upcase == s
  end

end
