class Bob
  def hey(words)
    return 'Woah, chill out!'   if yelling?(words)
    return 'Sure.'              if question?(words)
    return 'Fine. Be that way!' if silent?(words)
    'Whatever.'
  end

  private

  def question?(words)
    words.chars.to_a.last == '?'
  end

  def silent?(words)
    words.strip == ''
  end

  def yelling?(words)
    words.upcase == words && words.chars.any? { |c| ('A'..'Z').include?(c) }
  end
end
