class Bob
  def hey message
    s = message.gsub("\n", ' ')

    return 'Fine. Be that way!' if s.strip.empty?
    return 'Woah, chill out!'   if s.match(/^[^a-z]+!$|^(\s*[A-Z]\s*)+\??$/)
    return 'Sure.'              if s.match(/^.+\?$/)
    'Whatever.'
  end
end
