class Bob
  def hey(something)
    if something.nil? or something.strip.empty? then
      'Fine. Be that way!'
    elsif something.eql? something.upcase then
      'Woah, chill out!'
    elsif something.end_with? '?' then
      'Sure.'
    else
      'Whatever.'
    end
  end
end
