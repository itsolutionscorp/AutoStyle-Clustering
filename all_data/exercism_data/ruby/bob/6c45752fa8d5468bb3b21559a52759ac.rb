# encoding: utf-8
require "unicode_utils/upcase"

class Bob

  def hey(word = nil)
    return "Fine. Be that way!" if word =~ /\A\s*\Z/
    return "Woah, chill out!" if UnicodeUtils.upcase(word) == word
    return "Sure." if word[-1] == "?"
    "Whatever."
  end

end
