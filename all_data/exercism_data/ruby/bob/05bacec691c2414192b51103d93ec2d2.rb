class Bob
  def hey sentence
    if sentence.strip == ''
      'Fine. Be that way!'
    elsif sentence == sentence.upcase && sentence =~ /[A-Za-z]/
      'Woah, chill out!'
    elsif sentence.split("\n").last =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
