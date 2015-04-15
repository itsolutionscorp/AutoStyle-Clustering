# ruby-2.0.0-p353

class Bob

  Silence = /\A *\Z/
  Question = /\?\Z/
  Shouting = %r(
    \A[^a-z]*  # Starts with anything but lowercase.
    [A-Z]+     # At least one capital letter.
    [^a-z]*\Z  # Ends with anything but lowercase.
  )x

  def hey(query)
    case query
    when Silence
      'Fine. Be that way!'
    when Shouting
      'Woah, chill out!'
    when Question
      'Sure.'
    else
      'Whatever.'
    end
  end

end
