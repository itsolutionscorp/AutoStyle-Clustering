class Bob
  def hey(input)
    data = input.strip
    return "Fine. Be that way!" if is_it_silence?  data
    return "Woah, chill out!"   if is_it_shouting? data
    return "Sure."              if is_it_question? data
    "Whatever."
  end

  def is_it_silence?(data)
    data == ''
  end

  def is_it_shouting?(data)
    data == data.upcase && data != data.downcase
  end

  def is_it_question?(data)
    data.end_with? '?'
  end
end
