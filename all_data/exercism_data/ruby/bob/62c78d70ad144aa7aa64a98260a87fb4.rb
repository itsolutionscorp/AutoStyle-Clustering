class Bob 
  def hey(msg)
    output = 'Whatever.'

    if msg.strip == ''
      output = 'Fine. Be that way!'
    elsif msg.gsub(/([A-Z]+|\W+|\d+)/, '') == ''
      output = 'Woah, chill out!'
    elsif msg[-1] == '?'
      output = 'Sure.'
    end

    output
  end
end
