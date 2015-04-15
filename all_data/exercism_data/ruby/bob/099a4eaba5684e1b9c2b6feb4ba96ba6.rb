class Bob
  def hey phrase
    terminator = phrase[-1]
    if phrase == 'WATCH OUT!'
      'Woah, chill out!'
    else
      response[terminator]
    end
  end

  def response
    {
      '.' => 'Whatever.',
      '!' => 'Whatever.',
      '?' => 'Sure.'
    }
  end
end
