class Bob

  def hey(s)
    if s.upcase == s && s.downcase != s then 'Whoa, chill out!'
    elsif s[-1,1] == '?' then 'Sure.'
    elsif s.gsub(' ','').gsub("\t", '').empty? then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

end
