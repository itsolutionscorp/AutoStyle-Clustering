class Bob
  def hey(str)
    return 'Fine. Be that way!' if str.nil? || str.strip.empty?
    return 'Woah, chill out!' if str == str.upcase
    return 'Sure.' if str.index(/\?$/)
    return 'Whatever.'
  end
end
