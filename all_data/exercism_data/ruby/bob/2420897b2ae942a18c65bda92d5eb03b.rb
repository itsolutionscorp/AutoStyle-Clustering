class Bob

  def hey(string)
    is_it = Helper.new(string)

    if is_it.blank?
      return "Fine. Be that way!"
    elsif is_it.capitalized?
      return "Woah, chill out!"
    elsif is_it.question?
      return "Sure."
    else
      "Whatever."
    end
  end

end

class Helper
  def initialize(string)
    @string = string
  end

  def blank?
    @string == nil || @string.strip == ""
  end

  def question?
    @string.end_with? "?"
  end

  def capitalized?
    @string == @string.upcase
  end
end
