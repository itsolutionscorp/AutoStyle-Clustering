class Bob

  def hey(text)
    feedback(text)
  end

  def feedback(text)
    case
    when text.match(/\n/)
	whatever
    when text.empty? || text.match(/^ *$/)
	fine
    when text.match(/\t/)
	fine
    when text.match(/\.$/) || text.match(/\d$/)
	whatever
    when text.upcase != text && text.match(/!$/)
	whatever
    when text.match(/\d\?$/)
	sure	
    when text == text.upcase
	chill
    when text.match(/\?$/)
	sure
    end
  end

  def fine
    'Fine. Be that way!'
  end

  def sure
    'Sure.'
  end

  def chill
    'Whoa, chill out!'
  end

  def whatever
    'Whatever.'
  end

end
