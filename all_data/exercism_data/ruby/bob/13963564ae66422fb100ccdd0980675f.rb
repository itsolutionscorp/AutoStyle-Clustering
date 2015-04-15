class Bob
  @@responses = {
    silent?:    "Fine. Be that way!",
    yell?:      "Woah, chill out!",
    question?:  "Sure.",
  }

  def hey msg=""
    @@responses.each do |checker, response|
      return response if msg.send(checker)
    end
    return "Whatever."
  end
end

String.class_eval do
  def last
    self[self.length-1]
  end

  def question?
    last == "?"
  end

  def yell?
    self.upcase == self && /[[:alpha:]]/.match(self)
  end

  def silent?
    split.empty?
  end 
end
