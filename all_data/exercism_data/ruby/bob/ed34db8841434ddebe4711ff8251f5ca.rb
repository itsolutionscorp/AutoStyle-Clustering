class Bob
  def hey(phrase)
    silent = ->(x) { x.strip == '' }
    yelled = ->(x) { x.upcase == x && x.scan(/[[:alpha:]]/).size > 0 }
    asked = ->(x) { x.end_with?('?') }
    
    case phrase
    when silent then 'Fine. Be that way!'
    when yelled then 'Woah, chill out!'
    when asked then 'Sure.'
    else 'Whatever.'
    end
  end
end