class Bob
  def hey(hey)
    case hey
    when ->(hey) { hey =~ /[A-Z]/ && (hey.upcase == hey) }
      'Woah, chill out!'
    when /\?\Z/
      'Sure.'
    when /\A\s*\Z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
