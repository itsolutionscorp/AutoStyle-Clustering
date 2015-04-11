class Bob
  require 'active_support/core_ext/object/blank'
  def hey(heard)
    if heard.to_s.strip.blank?
      return "Fine. Be that way!"
    elsif heard.upcase==heard
      return "Woah, chill out!"
    elsif heard.end_with?('?')
      return "Sure."
    else
      return "Whatever."
    end
  end
end
