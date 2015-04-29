class Bob
  def hey(phrase)
    Phrase.parse_phrase(phrase)
  end
end

class Phrase
  def self.parse_phrase(phrase)
    if phrase.strip == ""
      Response.silence
    elsif phrase.upcase == phrase && phrase.match(/[a-zA-Z]/)
      Response.shouting
    elsif phrase[-1] == "?"
      Response.question
    else
      Response.yelling
    end
  end
end

class Response
  def self.shouting
    "Woah, chill out!"
  end

  def self.question
    "Sure."
  end

  def self.yelling
    "Whatever."
  end

  def self.silence
    "Fine. Be that way!"
  end
end
