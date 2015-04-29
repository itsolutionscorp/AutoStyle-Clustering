class Bob
  def hey(text)

    case text
    when /.*\?$/
      'Sure.'
    when /^[A-Z\b\W\d]+$/
      'Woah, chill out!'
    when ->(val){String(val) == "" }
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end
end
