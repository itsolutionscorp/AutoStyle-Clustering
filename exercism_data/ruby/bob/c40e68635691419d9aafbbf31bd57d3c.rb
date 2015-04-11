class Bob
  def hey(msg)
    smallcase = msg.gsub(" ", "").scan(/([a-z]+)/).join("")
    bigcase = msg.gsub(" ", "").scan(/([A-Z]+)/).join("")
    digits = msg.gsub(" ", "").scan(/\d/).join("")
    last = msg[-1]
    if msg.strip.empty?
      "Fine. Be that way!"
    elsif smallcase.empty? and !bigcase.empty?
      "Woah, chill out!"
    elsif last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
 end
