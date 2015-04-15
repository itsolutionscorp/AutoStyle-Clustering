class Bob
  def hey(string)
    if string.nil? || string.strip == ''
      'Fine. Be that way!'
    elsif string.upcase == string
      "Woah, chill out!"
    elsif string.end_with? '?'
      'Sure.'
    else
      "Whatever."
    end
  end
end
