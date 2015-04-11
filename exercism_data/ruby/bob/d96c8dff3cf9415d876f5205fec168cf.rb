# question -> 'Sure.'
# CAPS -> 'Woah, chill out!'
# nil -> 'Fine. Be that way!'
# else -> 'Whatever.

class Bob
  
  def hey(msg)
    if msg.nil? or msg.empty? or msg.gsub(/[[:blank:]]/,'').empty?
      'Fine. Be that way!'
    elsif msg.upcase!.nil?
      'Woah, chill out!'
    elsif msg.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

end
