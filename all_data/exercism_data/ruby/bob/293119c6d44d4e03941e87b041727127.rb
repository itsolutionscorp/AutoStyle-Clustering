#Inheritance taken to its illogical conclusion.

class Animal
  def hey(message)
    "Whatever."
  end
end

class Person < Animal
  def hey(message)
    yelling?(message) ? "Woah, chill out!" : super
  end

  def yelling?(message)
    m = message.gsub(/[^A-Za-z]+/, "")
    !!m.match(/\A[A-Z]+\Z/)
  end
end

class Teenager < Person
  def hey(message)
    question?(message) ? "Sure." : super
  end

  def question?(message)
    !!message.match(/\?\Z/)
  end
end

class Bob < Teenager
  def hey(message)
    silence?(message) ? "Fine. Be that way." : super
  end

  def silence?(message)
    message.nil? || message == ""
  end
end
