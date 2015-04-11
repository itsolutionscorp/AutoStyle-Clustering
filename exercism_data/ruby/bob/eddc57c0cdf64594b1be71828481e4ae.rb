class Bob
  def hey(input)
    input.strip!

    if silent?(input)
      'Fine. Be that way!'
    elsif shout?(input)
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    else 
      'Whatever.'
    end
  end

  def silent?(input)
    input.empty?
  end

  def shout?(input)
    input == input.upcase
  end

  def question?(input)
    input.end_with?('?')
  end
end
