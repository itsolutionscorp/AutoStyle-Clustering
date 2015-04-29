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
    s.nil? || s.strip.empty?
  end

  def question?(s)
    s.end_with?('?')
  end

  def shouting?(s)
    s.upcase.eql?(s)
  end

end
