module SentenceMoods

  def silent?(heard)
    heard.empty?
  end

  def question?(heard)
    heard.end_with?('?')
  end

  def shout?(heard)
    heard == heard.upcase
  end

end

class Bob
  include SentenceMoods

  def hey(message)
    heard = message.to_s
    case 
    when silent?(heard) then  "Fine. Be that way."
    when question?(heard) then "Sure." 
    when shout?(heard) then  "Woah, chill out!"
    else "Whatever."
    end
  end

end
