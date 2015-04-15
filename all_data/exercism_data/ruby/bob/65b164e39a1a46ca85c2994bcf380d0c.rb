class Bob
  def hey( something )
    case SentenceAnalyzer.analyze( something )
    when :blank
      return "Fine. Be that way!"
    when :shoutout
      return "Woah, chill out!"
    when :question
      return "Sure."
    else
      "Whatever."
    end
  end
end

class SentenceAnalyzer
  def self.analyze( sentence )
    return :blank if sentence.blank?
    return :shoutout if sentence.shouting? 
    return :question if sentence.question?
  end
end

class String
  def shouting?
    self == self.upcase
  end

  def question?
    self[-1] == "?"
  end

  def blank?
    self.gsub(/\s+/,"") == ""
  end
end
