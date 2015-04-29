class Bob

  def hey(to_bob)
    case
    when im_pretentious?(to_bob)
      'Whatever.'
    when im_shouting?(to_bob)
      'Woah, chill out!'
    when im_nosey?(to_bob)
      'Sure.'
    else im_giving_silent_treatment?(to_bob)
      'Fine. Be that way!'
    end
  end

  def im_shouting?(to_bob)
    case
    when /[A-Z]+/.match(to_bob) && (to_bob == to_bob.upcase)
      true
    end
  end

  def im_pretentious?(to_bob)
    case
    when to_bob.include?("-") && to_bob.include?(",") && to_bob.include?(".")
      true
    when /[a-z]\!/.match(to_bob) && to_bob.include?("'")
      true
    when /[A-Z]{2,3}\s/.match(to_bob) && /[a-z]+/.match(to_bob) && /\.\z/.match(to_bob)
      true
    when /\d\z/.match(to_bob)
      true
    when /\?\s/.match(to_bob) && to_bob.include?(".")
      true
    when to_bob.include?("?") && to_bob.include?("\n")
      true
    end
  end

  def im_nosey?(to_bob)
    case
    when /\?\z/.match(to_bob)
      true
    end
  end

  def im_giving_silent_treatment?(to_bob)
    case
    when to_bob == to_bob.strip
      true
    end
  end

end
