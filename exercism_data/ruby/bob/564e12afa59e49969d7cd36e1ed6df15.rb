class Bob

  def hey(to_bob)
    case
    when im_shouting(to_bob)
      'Woah, chill out!'
    when im_nosey(to_bob)
      'Sure.'
    when im_pretentious(to_bob)
      'Whatever.'
    else
      'Fine. Be that way!'
    end
  end

  def im_shouting(to_bob)
    /[A-Z+]/.match(to_bob) && to_bob == to_bob.upcase
  end

  def im_nosey(to_bob)
    /\?\z/.match(to_bob)
  end

  def im_pretentious(to_bob)
    /[a-zA-Z]/.match(to_bob) || /[\d+]/.match(to_bob)
  end
end
