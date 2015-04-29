class Bob
  def hey(whats_up)
    return 'Fine. Be that way.' if whats_up.nil? or whats_up.length == 0
    return 'Woah, chill out!'   if whats_up == whats_up.upcase
    return 'Sure.'              if whats_up.end_with?('?')
    return 'Whatever.'
  end
end
