class Bob

  def hey(str)
    if shout?(str)
      "Woah, chill out!"
    elsif question?(str)
      "Sure."
    elsif silence?(str)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def question?(arg)
    arg.match(/[?]$/) && not_multiline?(arg)
  end

  def shout?(arg)
    arg == arg.upcase && arg.match(/[^0-9\W]/)
  end

  def silence?(arg)
    arg.match(/^\s*$/) && not_multiline?(arg)
  end

  def not_multiline?(arg)
    ! arg.match(/\n/)
  end
end
