class Bob
  
  
  def hey(something)
      something = something.to_s
      if something === something.upcase && something.end_with?('?')
        "Woah, chill out!"
      elsif something.end_with?('?')
        "Sure."
      elsif something === something.upcase && something != '' 
        "Woah, chill out!"
      elsif something == ''
        "Fine. Be that way!"
      else
        "Whatever."
    end
  end
  
  
  
end
