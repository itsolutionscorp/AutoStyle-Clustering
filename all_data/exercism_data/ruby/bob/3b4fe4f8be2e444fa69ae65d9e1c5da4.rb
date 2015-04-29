class Bob
 
  def hey(a) 
    case
      when a.nil? || a.strip.empty? 
        'Fine. Be that way!'
      when a === a.upcase || ( a === a.upcase && a.end_with?("?") )
        "Woah, chill out!"
      when a.match(/\?$/)
        "Sure."    
      else
        "Whatever."
    end
  end

end 
