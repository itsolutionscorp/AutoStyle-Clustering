class Teenager
  class Rule
    attr_accessor :matcher, :response

    def initialize(matcher = nil, response = nil)
      self.response = response || "Whatever."
      self.matcher = matcher || /./
    end

    def match?(comment)
      comment =~ matcher
    end
  end

  def initialize
    @rules = []
  end

  def hey(comment)
    response(comment)
  end

  def response(comment)
    (@rules + [Rule.new]).each do |rule|
      return rule.response if rule.match?(comment)
    end
  end
end

class Bob < Teenager
  def initialize
    super
    @rules += [silence?, all_caps?, question?]
  end
  
  def all_caps?
    Rule.new(/\A[^a-z]*\z/, "Woah, chill out!")
  end

  def silence?
    Rule.new(/\A\s*\z/, "Fine. Be that way!")
  end

  def question?
    Rule.new(/\?\z/, "Sure.")
  end
end

  #def hey(message)
  #  return "Fine. Be that way!" if silence?(message)
  #  return "Woah, chill out!" if upcase?(message)
  #  return "Sure." if question?(message)
  #  "Whatever."
  #end

  #private
  #  def upcase?(str)
  #    str == str.upcase
  #  end

  #  def question?(str)
  #    str.chars.last == '?'
  #  end

  #  def silence?(str)
  #    (str =~ /\A\s*\z/) != nil
  #  end
#end
