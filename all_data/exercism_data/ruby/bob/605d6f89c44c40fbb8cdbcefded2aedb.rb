#!/usr/bin/env ruby

class Bob

  def hey(strings)
    if strings.strip == ''
      return "Fine. Be that way!"
    elsif strings == strings.upcase
      return "Woah, chill out!"
    elsif strings[-1,1] == '?'
      return "Sure."
    else
      return "Whatever."
    end
  end
end
