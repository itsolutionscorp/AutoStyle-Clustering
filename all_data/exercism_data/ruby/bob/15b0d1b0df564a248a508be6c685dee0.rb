class Bob
  def hey(greeting)
    case greeting.to_s
      when /^\s*$/
        'Fine. Be that way!'
      when /^[^a-z]+$/
        'Woah, chill out!'
      when /\?$/
        'Sure.'
      else
        'Whatever.'
      end
  end
end
