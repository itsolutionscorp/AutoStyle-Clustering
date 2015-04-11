#!/usr/bin/ruby

class Bob
  def hey(arg)
    if uppercase?(arg) or asking_forceful?(arg)
      "Woah, chill out!"
    elsif asking?(arg)
      "Sure."
    elsif silent?(arg)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def silent?(string)
    true if string.strip == ''
  end

  def uppercase?(string)
    true if string == string.upcase and string =~ /[A-Z]/
  end

  def asking_forceful?(string)
    true if uppercase?(string) and string.end_with?('?')
  end

  def asking?(string)
    true if string.end_with?('?')
  end
end
