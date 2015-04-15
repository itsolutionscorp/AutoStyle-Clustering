require 'minitest/autorun'

class Bob

  def hey(input)
    if silence?(input)
      "Fine. Be that way!"
    elsif yelling?(input)
      "Woah, chill out!"
    elsif question?(input)
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?(input)
    input.gsub(" ","") == ""
  end

  def yelling?(input)
    input == input.upcase
  end

  def question?(input)
    input.end_with?("?")
  end

end
