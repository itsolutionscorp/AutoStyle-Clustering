require 'active_support/core_ext/object/blank'

class Bob
  def hey(sentence)
    case
    when sentence.blank?
      'Fine. Be that way.'
    when sentence.end_with?('?')
      'Sure.'
    when sentence == sentence.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
