class Bob
  attr_accessor :text

  def hey(input)
    @text = input.to_s
    return 'Fine. Be that way!' if not_talking?
    return 'Woah, chill out!' if yelling?
    return 'Sure.' if questioning?
    return 'Whatever.'
  end

  private

  def not_talking?
    text.length == 0
  end

  def yelling?
    text == text.upcase
  end

  def questioning?
    text.end_with?("?")
  end
end
