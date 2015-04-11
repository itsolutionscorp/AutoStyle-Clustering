class Bob
  attr_accessor :text

  def hey(input)
    self.text = input.to_s.strip
    return 'Fine. Be that way!' if not_talking?
    return 'Woah, chill out!' if yelling?
    return 'Sure.' if questioning?
    return 'Whatever.'
  end

  private

  def not_talking?
    self.text.length == 0
  end

  def yelling?
    self.text == self.text.upcase
  end

  def questioning?
    self.text[-1] == "?"
  end
end
