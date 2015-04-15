class Bob
  def hey(arg)
    if nothings?(arg)
      "Fine. Be that way!"
    elsif capslocked?(arg)
      "Woah, chill out!"
    elsif question?(arg)
      "Sure."
    else
      "Whatever."
    end
  end
end

def capslocked?(arg)
  true if arg.upcase == arg
end

def nothings?(arg)
  true if arg.nil? || arg.empty? || arg.include?("  ")
end

def question?(arg)
  true if arg[-1] == "?"
end
