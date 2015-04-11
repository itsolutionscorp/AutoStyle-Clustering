# Bob is an unruly teen, or at least a decent imitation of one.
class Bob

  # Bob#hey takes an input string and returns Bob's response as a string.
  def hey input
    return 'Fine. Be that way.' if not input or input.empty?
    return 'Sure.' if input[-1] == '?'
    return 'Woah, chill out!' if input == input.upcase
    'Whatever.'
  end
end
