# exercism - ruby/bob
class Bob
  def hey(s)
    s = s.delete "\n"
    silent = s.match(/^[\s]*$/)
    shouty = (s.match(/[A-Z]/) and (s == s.upcase))
    asking = s.match(/^.*\?$/)
    return 'Fine. Be that way!' if silent
    return 'Woah, chill out!'   if shouty
    return 'Sure.'              if asking
    'Whatever.'
  end
end
