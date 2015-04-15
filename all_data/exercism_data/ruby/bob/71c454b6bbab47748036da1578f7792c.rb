class Bob
  def hey(s)
    if silence?(s)
      "Fine. Be that way!"
    elsif shout?(s)
      "Whoa, chill out!"
    elsif question?(s)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silence?(s)
    s.empty? or s.match(/^\s+$/)
  end

  def shout?(s)
    s.match(/[A-Z]/) and s == s.upcase
  end

  def question?(s)
    s.end_with?("?")
  end
end
