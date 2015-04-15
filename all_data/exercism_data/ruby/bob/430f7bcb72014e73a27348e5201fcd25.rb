class Bob
  def hey(talk)
    if talk.to_s.gsub(/\s/, "").empty?
      "Fine. Be that way!"
    elsif talk == talk.upcase
      "Woah, chill out!"
    elsif /\?\z/ =~ talk
      "Sure."
    else
      "Whatever."
    end
  end
end
