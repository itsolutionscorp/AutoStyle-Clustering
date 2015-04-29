class Bob
  def hey what
    case what
    when Silent
      "Fine. Be that way!"
    when AllCaps
      "Woah, chill out!"
    when Question
      "Sure."
    when EverythingElse
      "Whatever."
    end
  end

  private

  class Silent
    def self.=== string
      string.to_s.strip == ""
    end
  end

  class AllCaps
    def self.=== string
      string.match /^([^a-z]*[A-Z]+[^a-z]*)+$/
    end
  end

  class Question
    def self.=== string
      string.end_with? '?'
    end
  end

  class EverythingElse
    def self.=== _
      true
    end
  end
end
