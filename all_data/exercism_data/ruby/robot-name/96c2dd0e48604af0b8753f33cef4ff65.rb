class Robot

  def name
    @name ||= RobotName.generate
  end

  def reset
    @name = nil
  end

end

class RobotName

  def self.generate
    letters+numbers
  end

  private

    def self.letters
      (lowercase_chars + uppercase_chars).shuffle.take(2).join
    end

    def self.lowercase_chars
      ('a'..'z').to_a
    end

    def self.uppercase_chars
      ('A'..'Z').to_a
    end

    def self.numbers
      zero_padded rand(999)
    end

    def self.zero_padded( number )
      "%03d" % number
    end

end
