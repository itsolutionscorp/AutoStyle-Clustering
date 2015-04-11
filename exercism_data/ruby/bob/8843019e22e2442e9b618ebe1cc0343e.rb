class Bob
  def hey ( text )
    if ( text =~ /^[\s]*$/ )  
      return 'Fine. Be that way!'
    end
    
    if text
      return 'Whatever.'
    end
  end
end
