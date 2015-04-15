class Bob
  def hey(what)
    return "Fine. Be that way!" if what.strip.empty?
    return "Woah, chill out!" if shouted?(what)
    return "Sure." if question?(what)
    "Whatever."
  end

  private
  def shouted?(s)
    s.match(/[[:alpha:]]/) and s.match(/[[:lower:]]/).nil?
  end
                  
  def question?(q)
    q.end_with?("?")
  end
end
