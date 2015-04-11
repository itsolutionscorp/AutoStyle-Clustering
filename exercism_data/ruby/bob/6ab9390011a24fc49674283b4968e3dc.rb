class Bob
  def hey(string)
    case
    when string.nil? || string.strip.empty? then 'Fine. Be that way!'
    when string.upcase == string            then 'Woah, chill out!'
    when string.end_with?("?")              then 'Sure.'
    else                                         'Whatever.'
    end
  end
end
