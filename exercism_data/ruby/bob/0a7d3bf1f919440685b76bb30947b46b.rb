class Bob
  def hey(string)
    return 'Fine. Be that way!' if blank?(string)
    return 'Woah, chill out!' if all_caps?(string)
    return 'Sure.' if question?(string)
    'Whatever.'
  end

  private

  def blank?(string)
    string.nil? || string.empty?
  end

  def question?(string)
    string[-1] == "?"
  end

  def all_caps?(string)
    string == string.upcase
  end
end
