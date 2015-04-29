class Bob
  SHOUT     = /\A[^a-z]+\Z/
  NOTHING   = /\A\Z/
  QUESTION  = /\?\Z/

  def hey phrase=nil

    case phrase.to_s
    when NOTHING  then "Fine. Be that way." 
    when SHOUT    then "Woah, chill out!"
    when QUESTION then "Sure." 
                  else "Whatever." end
  end
end
