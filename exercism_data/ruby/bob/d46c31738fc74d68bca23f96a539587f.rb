class Bob
  def hey(sentence)
    case sentence.split("\n").last || ''
    when /^\s*$/         then 'Fine. Be that way!'
    when sentence.upcase then 'Woah, chill out!'
    when /\?$/           then 'Sure.'
    else 'Whatever.'
    end
  end
end
