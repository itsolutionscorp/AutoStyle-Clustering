class Bob
  def hey(fraze)
    return 'Fine. Be that way.' if fraze.nil? || fraze.empty?
    return 'Sure.'              if fraze =~ /\?\Z/
    return 'Woah, chill out!'   if fraze.upcase == fraze
    'Whatever.'
  end
end
