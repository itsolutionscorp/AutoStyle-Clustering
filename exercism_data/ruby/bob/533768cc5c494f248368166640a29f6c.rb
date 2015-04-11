class Bob
  def hey(message)
    response parse(message)
  end

  private
  def parse(message)
    sentence = message.gsub("\n", ' ')
    return :silence  if sentence.strip.empty?
    return :yell     if sentence.match(/^[^a-z]+!$|^(\s*[A-Z]\s*)+\??$/)
    return :question if sentence.match(/^.+\?$/)
  end

  def response quest
    case quest
    when :question  then 'Sure.'
    when :yell      then 'Woah, chill out!'
    when :silence   then 'Fine. Be that way!'
    else                 'Whatever.'
    end
  end
end
