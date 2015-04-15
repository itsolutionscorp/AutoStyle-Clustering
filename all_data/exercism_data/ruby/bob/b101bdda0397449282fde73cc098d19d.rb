class Bob
  def hey(prompt)

    prompt = Prompt.new prompt.to_s

    if prompt.empty?
      'Fine. Be that way.'
    elsif prompt.yelling?
      'Woah, chill out!'
    elsif prompt.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Prompt < String

  def yelling?
    self == upcase
  end
end
