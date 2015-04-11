class Bob
  def hey(saying)
    if saying == ""
      'Fine, be that way.'
    elsif saying == saying.upcase
      "Woah, chill out!"
    elsif saying[-1] == "?"
      'Sure.'
    else
      "Whatever."
    end
  end
end
