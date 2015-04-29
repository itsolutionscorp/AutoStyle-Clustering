class Bob
  
  def hey(str)
    stripped_string = str.scan(/[a-zA-Z]+/).join
    if stripped_string.upcase.eql?(stripped_string) && !stripped_string.empty?
      'Whoa, chill out!'
    elsif str[-1].eql? '?'
      'Sure.'
    elsif str.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
