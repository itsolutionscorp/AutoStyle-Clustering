class Bob
  def hey(something)
    if something.nil? or something.strip == '' then
      'Fine. Be that way!'
    elsif something == something.upcase then
      'Woah, chill out!'
    elsif something[-1] == '?' then
      'Sure.'
    else
      'Whatever.'
    end
  end
end
