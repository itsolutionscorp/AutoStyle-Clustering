class Bob
  def hey(string)
    string.lstrip! unless string.nil?
    if string.nil? or string.empty? then 'Fine. Be that way!'
    elsif string == string.upcase   then 'Woah, chill out!'
    elsif string.end_with?('?')     then 'Sure.'
    else                                 'Whatever.'
    end
  end
end

bob = Bob.new
