class Teenager  
  def rules
  end

  def hey(comment)
    response(comment)
  end

  def response(comment)
    rules.each do |rule|
      return rule[1] if comment =~ rule[0]
    end
  end
end

class Bob < Teenager
  def rules
    [
      [/\A\s*\z/, "Fine. Be that way!"], # silence
      [/\A[^a-z]*\z/, "Woah, chill out!"], # ALL CAPS
      [/\?\z/, "Sure."], # Question
      [/./, "Whatever."] #catch all
    ]
  end
end
