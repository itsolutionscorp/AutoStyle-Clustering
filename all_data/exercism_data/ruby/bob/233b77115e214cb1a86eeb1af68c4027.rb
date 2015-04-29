class Bob

def hey(arg1)
  cleaned = arg1.tr(' ', '')
  return "Fine. Be that way!" if cleaned.empty?
  return "Woah, chill out!" if Bob::is_shouting(cleaned)
  return "Sure." if cleaned.chomp.end_with?('?')
  "Whatever."
end

def self.is_shouting(phrase)
  chomped = phrase.tr('^A-Za-z', '') #remove everything that isn't a character
  return false if chomped.length == 0
  return (chomped.count("A-Z") == chomped.length)
end

end
