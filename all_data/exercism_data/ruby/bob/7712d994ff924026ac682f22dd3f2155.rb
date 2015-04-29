# bob.rb

class Bob
  def hey stuff
    @stuff = stuff.strip.gsub /\d/, ''
    return 'Fine. Be that way!' if silence?
    return 'Whoa, chill out!' if shouting?
    return 'Sure.' if question?
    'Whatever.'
  end

  def silence?
    @stuff.empty?
  end

  def shouting?
    upcase = @stuff.gsub(/[^A-Z]/,'')
    letters_only = @stuff.gsub(/\W/, '')
    return false if upcase.empty?
    upcase == letters_only
  end

  def question?
    '?' == @stuff.chars.last
  end

end
