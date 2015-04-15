require 'rubygems'
require 'unicode'

class Bob
  def hey(inquiry)
  	if empty?(inquiry)
  	  "Fine. Be that way!"
    elsif shouting?(inquiry)
      "Woah, chill out!"   
    elsif question?(inquiry)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def empty?(str)
  	str.strip == ''
  end

  def shouting?(str)
  	str.match(%r{[A-Z]}) && ::Unicode.upcase(str) == str
  end

  def question?(str)
  	str.match(%r{\?\z})
  end
end
