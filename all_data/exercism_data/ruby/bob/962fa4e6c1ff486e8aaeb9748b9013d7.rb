class Bob
  def hey(sentence)
    case
      when empty?(sentence) then
        'Fine. Be that way!'
      when yelling?(sentence) then
        'Woah, chill out!'
      when question?(sentence) then
        'Sure.'
      else
        'Whatever.'
    end
  end
  def empty?(sentence)
    sentence.strip.empty?
  end
  def yelling?(sentence)
    sentence.upcase == sentence and sentence.upcase != sentence.downcase
  end
  def question?(sentence)
    sentence.end_with?('?')
  end
end
