module Helpers
  def blank?(string)
    string.to_s.strip == ""
  end

  def question?(string)
    string.end_with? "?"
  end

  def capitalized?(string)
    string == string.upcase
  end
end

class Bob
  include Helpers

  def hey(string)
    if blank?(string)
      "Fine. Be that way!"
    elsif capitalized?(string)
      "Woah, chill out!"
    elsif question?(string)
      "Sure."
    else
      "Whatever."
    end
  end
end
