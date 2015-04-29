class Bob
  def hey phrase
    terminator = phrase[-1]
    if phrase == 'WATCH OUT!'
      'Woah, chill out!'
    elsif terminator == '.' || terminator == '!'
      'Whatever.'
    elsif terminator == '?'
      'Sure.'
    end
  end
end
