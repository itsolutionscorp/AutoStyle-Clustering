class Bob
  def hey(input)
      case
      when silent_treatment?(input)
        'Fine. Be that way!'
      when yelling_at_bob?(input)
        'Woah, chill out!'
      when questioning_bob?(input)
        'Sure.'
      else
        'Whatever.'
      end
    end
  end

  def yelling_at_bob?(input)
    input.strip.upcase == input
  end

  def questioning_bob?(input)
    input.strip.end_with?('?')
  end

  def silent_treatment?(input)
    input.strip.empty?
  end
