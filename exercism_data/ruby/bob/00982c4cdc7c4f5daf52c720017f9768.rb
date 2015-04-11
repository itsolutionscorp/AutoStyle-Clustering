class Bob
  def hey(message)
    case message.strip
    when /^\s*$/
      'Fine. Be that way!'
    when lambda { |m| letters = m.gsub(/[^a-zA-Z]/, ''); !letters.empty? && (letters == letters.upcase) }
      'Whoa, chill out!'
    when lambda { |m| m[-1] == '?' }
      'Sure.'
    else
      'Whatever.'
    end
  end
end
