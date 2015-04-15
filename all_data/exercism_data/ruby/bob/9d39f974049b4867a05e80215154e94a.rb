class Bob
  def hey(str)
    if str.strip.empty? then
      'Fine. Be that way!'
    elsif str.upcase == str then
      'Woah, chill out!'
    elsif str[-1,1] == '?' then
      'Sure.'
    else
      'Whatever.'
    end
  end
end
