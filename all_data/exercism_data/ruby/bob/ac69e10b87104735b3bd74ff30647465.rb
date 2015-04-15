require 'active_support/core_ext/object/blank'

class Bob
  def hey(sentence)
    return 'Fine. Be that way.' if sentence.blank?
    return 'Sure.' if sentence.end_with?('?')
    return 'Woah, chill out!' if sentence == sentence.upcase
    'Whatever.'
  end
end
