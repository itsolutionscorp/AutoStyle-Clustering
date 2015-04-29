class Bob
  def hey(babble)
    case
    when reticent?(babble)
      'Fine. Be that way!'
    when loud?(babble)
      'Woah, chill out!'
    when questioning?(babble)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def reticent?(s)
    s.nil? || s.strip.empty?
  end

  def questioning?(s)
    s.end_with?('?')
  end

  def loud?(s)
    s.upcase == s
  end
end
