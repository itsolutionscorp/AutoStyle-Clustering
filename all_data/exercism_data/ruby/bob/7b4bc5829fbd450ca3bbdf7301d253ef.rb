class Bob
  def hey(mje)
      if mje.strip == "" then
        "Fine. Be that way!"
      elsif mje == mje.upcase and mje.match(/[a-zA-Z]/) then
        "Woah, chill out!"
      elsif mje.end_with? '?' then 
        "Sure."
      else
        "Whatever."
      end
  end
end
