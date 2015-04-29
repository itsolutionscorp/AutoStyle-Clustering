class Response

  def self.fitting_for?(phrase)
    true
  end

  def self.give
    "Whatever."
  end

end

class SilentTreatment < Response
  def self.fitting_for?(phrase)
    phrase.strip == ''
  end

  def self.give
    "Fine. Be that way!"
  end
end

class Defensive < Response
  def self.fitting_for?(phrase)
    phrase.match(/.*[a-zA-Z].*/) and phrase == phrase.upcase
  end

  def self.give
    "Woah, chill out!"
  end
end

class FontOfKnowledge < Response
  def self.fitting_for?(phrase)
    phrase.split('').last == "?"
  end

  def self.give
    "Sure."
  end
end

class Bob
  @@repretoire = [SilentTreatment, Defensive, FontOfKnowledge, Response]

  def hey(phrase)
    appropriateResponse = @@repretoire.find { |response| response.fitting_for? phrase }

    appropriateResponse.give
  end

end
