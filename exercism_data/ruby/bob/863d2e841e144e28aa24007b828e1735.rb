# https://xkcd.com/208/
class Bob
  def hey(str)
    case str
    when /\A\s*\Z/
      "Fine. Be that way!"
    when /\A[^a-z]+\Z/
      "Woah, chill out!"
    when /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
