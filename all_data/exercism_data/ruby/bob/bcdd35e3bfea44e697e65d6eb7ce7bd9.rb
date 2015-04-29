class Bob

  def hey(message)
    case message
    when SILENCE
      "Fine. Be that way!"
    when YELLING, SWEARING
      "Woah, chill out!"
    when QUESTIONING
      "Sure."
    else
      "Whatever."
    end
  end

  SILENCE     = ->(string) { string =~ ALL_BLANK }
  YELLING     = ->(string) { letters_in(string) =~ ONLY_CAPITALS }
  QUESTIONING = ->(string) { string.end_with?('?') }
  SWEARING    = ->(string) { string =~ ONLY_SWEARING }

  ALL_BLANK     = /\A[ \t]*\z/
  ONLY_CAPITALS = /\A[A-Z]+\z/
  ONLY_SWEARING = /\A[~!@#$%^&*?]+\z/

  def self.letters_in(string)
    string.gsub(/[^A-Za-z]/, '')
  end
end
