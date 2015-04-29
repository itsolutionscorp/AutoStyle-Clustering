class Bob
  def hey (verbage)
    case
    when silent?(verbage)
      'Fine. Be that way!'
    when yell?(verbage)
      'Woah, chill out!'
    when question?(verbage)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def silent? (verbage)
    verbage.strip.empty?
  end

  def question? (verbage)
    verbage.end_with?('?')
  end

  def yell? (verbage)
    verbage.upcase == verbage
  end
end
