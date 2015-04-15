# exercism - ruby/bob
class Bob
  def hey(s)
    s = s.delete "\n"
    if s.match(/^[\s]*$/)
      'Fine. Be that way!'
    elsif s.match(/[A-Z]/) and (s == s.upcase)
      'Woah, chill out!'
    elsif s.match(/^.*\?$/)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
