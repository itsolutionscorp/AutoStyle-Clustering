class Bob
  def hey(nonsense)
    if nonsense.strip.empty?
      'Fine. Be that way!'
    elsif nonsense.upcase == nonsense
      'Woah, chill out!'
    elsif nonsense.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
end
