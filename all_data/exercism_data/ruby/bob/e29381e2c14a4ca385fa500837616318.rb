class Bob
  def hey(string)
    case string
    when Silent
      "Fine. Be that way."
    when Question
      "Sure."
    when Shout
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  class Silent
    def self.===(other)
      other.nil? || other.empty?
    end
  end

  class Question
    def self.===(other)
      other.end_with?("?")
    end
  end

  class Shout
    def self.===(other)
      other == other.upcase
    end
  end
end
