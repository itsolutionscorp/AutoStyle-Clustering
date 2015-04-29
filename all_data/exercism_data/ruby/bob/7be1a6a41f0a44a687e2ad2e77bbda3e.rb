class Bob
  def hey data
    return 'Fine. Be that way!' if not data or (data.is_a?(String) and data.strip == '')
    return 'Woah, chill out!' if data.upcase == data
    return 'Sure.' if data[-1, 1] == '?'
    return 'Whatever.'
  end
end
