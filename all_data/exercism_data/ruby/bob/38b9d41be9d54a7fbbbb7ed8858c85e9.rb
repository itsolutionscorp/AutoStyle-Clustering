class Bob

  def hey(message)
    case message
    when SILENCE
      "Fine. Be that way!"
    when YELLING
      "Woah, chill out!"
    when QUESTIONING
      "Sure."
    else
      "Whatever."
    end
  end

  SILENCE     = ->(string) { string =~ ALL_BLANK }
  YELLING     = ->(string) { letters_in(string) =~ ONLY_CAPITALS }
  QUESTIONING = ->(string) { string =~ ENDS_IN_QUESTION }

  ALL_BLANK        = /\A[ \t]*\z/
  ENDS_IN_QUESTION = /\?\z/
  ONLY_CAPITALS    = /\A[A-Z]+\z/

  def self.letters_in(string)
    string.gsub(/[^A-Za-z]/, '')
  end
end
