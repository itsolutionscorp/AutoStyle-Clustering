class Bob
  def hey(query)
    return 'Woah, chill out!'   if shout?(query)
    return 'Fine. Be that way!' if silence?(query)
    return 'Sure.'              if query?(query)
    return 'Whatever.'
  end

  def query?(query)
    return !!(query.match(/\?\Z/))
  end

  def shout?(query)
    return !!(query.match(/[A-Z]/) && !query.match(/[a-z]/))
  end

  def silence?(query)
    return !!(query.match(/\A\s*\Z/))
  end
end
