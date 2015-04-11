class Bob
  def hey(text)
    greeting = Greeting.new(text)

    if greeting.silent?
      "Fine. Be that way!"
    elsif greeting.shouted?
      "Woah, chill out!"
    elsif greeting.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Greeting < String
  def question?
    end_with? "?"
  end

  def silent?
    lstrip.empty?
  end

  def shouted?
    upcase == to_s
  end
end
