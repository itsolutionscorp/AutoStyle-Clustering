class Bob
  def hey(phrase_string)
    phrase = Phrase.new(phrase_string)

    if phrase.silent?
      "Fine. Be that way!"
    elsif phrase.yelling?
      "Woah, chill out!"
    elsif phrase.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Phrase
  def initialize(string)
    @string = string
  end

  def silent?
    blank?
  end

  def yelling?
    uppercase_but_no_lowercase_letters?
  end

  def question?
    string.end_with?("?")
  end

  private

  def blank?
    string.strip.empty?
  end

  def uppercase_but_no_lowercase_letters?
    string =~ /[A-Z]/ && string !~ /[a-z]/
  end

  def string
    @string
  end
end
