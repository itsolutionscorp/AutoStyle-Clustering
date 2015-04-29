require 'active_support/core_ext/object/blank'

class Bob
  def hey(string)
    return 'Fine. Be that way!' if string.blank?
    return 'Woah, chill out!' if string == string.upcase
    return "Sure." if string =~ /\?$/

    "Whatever."
  end
end
