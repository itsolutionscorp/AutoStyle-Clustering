class Bob
  def hey(what)
    return "Fine. Be that way!" if what.strip.empty?
    return "Sure." if question?(what) and not shouted?(what)
    return "Woah, chill out!" if shouted?(what)
    "Whatever."
  end

  def shouted?(s)
    s.match(/[[:alpha:]]/) and s.match(/[[:lower:]]/).nil?
  end
                  
  def question?(q)
    q.end_with?("?")
  end
end
