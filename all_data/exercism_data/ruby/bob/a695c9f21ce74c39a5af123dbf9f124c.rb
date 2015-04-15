class Bob
  def hey greeting
    if greeting.strip.length==0
    'Fine. Be that way!'
    elsif greeting.upcase==greeting && greeting[/[a-zA-Z]/]!=nil
      'Woah, chill out!'
    elsif greeting[-1,1]=="?"
      "Sure."
    else
      'Whatever.'
    end
  end
end
