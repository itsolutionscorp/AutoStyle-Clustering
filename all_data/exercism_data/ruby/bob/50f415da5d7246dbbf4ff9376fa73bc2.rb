class Bob
  def hey phrase
    return 'Fine. Be that way!' if phrase.empty?
    terminator = phrase[-1]
    if needs_to_chill? phrase
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

  def needs_to_chill? phrase
    phrase.split.all? {|s| s.upcase == s }
  end
end
