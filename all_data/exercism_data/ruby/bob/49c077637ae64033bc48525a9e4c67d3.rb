class Bob
  def hey(phrase)
    silent = ->(x) { x.strip == '' }
    yelled = ->(x) { x =~ /[A-Z]/ && x.upcase == x }
    asked = ->(x) { x.end_with?('?') }
    
    case phrase
    when silent then 'Fine. Be that way!'
    when yelled then 'Woah, chill out!'
    when asked then 'Sure.'
    else 'Whatever.'
    end
  end
end
