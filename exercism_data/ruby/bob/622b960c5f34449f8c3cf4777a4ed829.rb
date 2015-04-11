class Bob
  def hey message
    case message
    when /\A[^a-z]*[A-Z]+[^a-z]*\Z/ then 'Woah, chill out!'
    when /\?\Z/ then 'Sure.'
    when /\A\s*\Z/ then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end
end
