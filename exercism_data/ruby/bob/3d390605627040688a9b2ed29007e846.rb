# Bob is an unruly teen, or at least a decent imitation of one.
class Bob
  def hey input
    return 'Fine. Be that way.' if not input or input.empty?
    return 'Sure.' if input.end_with? '?'
    return 'Woah, chill out!' if input == input.upcase
    'Whatever.'
  end
end
