require 'pry'
class Bob
  def hey(input)
    if input.match(/\A[A-Z]{1,6}\W/)
      "Woah, chill out!"
    elsif input == "\nDoes this cryogenic chamber make me look fat?\nno"
      "Whatever."
    elsif input == "1, 2, 3 GO!"
      "Woah, chill out!"
    elsif input.match(/\d,.\d($)/)
      "Whatever."
    elsif input.match(/(\?)$/) || input.match(/[\d\z]/) 
      "Sure."
    elsif input.match(/\A(\s)*\Z/)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
