class Bob
  def hey(prompt)
    return 'Fine. Be that way!' if silence?(prompt)
    return 'Woah, chill out!' if shout?(prompt)
    return 'Sure.' if question?(prompt)
    'Whatever.'
  end

  private

  def silence?(prompt)
    prompt.nil? || prompt.empty?
  end

  def question?(prompt)
    prompt[-1] == "?"
  end

  def shout?(prompt)
    prompt == prompt.upcase
  end
end
