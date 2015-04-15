class Bob
 def hey msg
  if msg.blank?
    return 'Fine. Be that way!'
  elsif (msg.downcase.chars & ('a'..'z').to_a).any?   && msg == msg.upcase && !msg.blank?
    return 'Woah, chill out!'
  elsif msg.end_with? '?'
    return 'Sure.'
  else
    return 'Whatever.'
  end
 end 
end

class String
  def blank?
    self !~ /[^[:space:]]/
  end
end
