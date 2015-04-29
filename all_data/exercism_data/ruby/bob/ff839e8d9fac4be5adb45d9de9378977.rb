require 'minitest/autorun'

class Bob

  def hey(input)
    if input.gsub(" ","") == ""
      return "Fine. Be that way!"
    elsif input == input.upcase
      return "Woah, chill out!"
    elsif input[-1] == '?'
      return "Sure."
    else
    return "Whatever."
    end
  end

end
