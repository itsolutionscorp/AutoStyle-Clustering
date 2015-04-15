class Bob
  def hey(s)
    if s == "" or s.match(/^\s+$/)
      return "Fine. Be that way!"
    end
    if s.match(/[A-Z]/) and s == s.upcase
      return "Whoa, chill out!"
    end
    if s.end_with?("?")
      return "Sure."
    end
    return "Whatever."
  end
end
