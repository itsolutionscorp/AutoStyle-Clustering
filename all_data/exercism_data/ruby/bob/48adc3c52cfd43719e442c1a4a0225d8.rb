class Bob
  def hey(input)
    case input.to_s
    when ->(i) { !(i =~ /\W/) }                       # no letters
      'Fine. Be that way.'
    when ->(i) { (i =~ /[A-Z]+/) && !(i=~ /[a-z]+/) } # shouty
      'Woah, chill out!'
    when ->(i) { i.end_with? '?' }
      'Sure.'
    else
      'Whatever.'
    end
  end
end
