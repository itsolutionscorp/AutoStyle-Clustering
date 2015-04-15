require 'pry'

class Bob

  def rules
    @rules = self.private_methods.grep(/rule_/).sort + [:default]
  end

  def hey(comment)
    rules.each do |rule|
      response = send(rule, comment)
      return response if response
    end
  end

  private

    def default(comment)
      return 'Whatever.'
      false
    end

    def rule_all_caps(comment)
      return "Woah, chill out!" if comment.upcase == comment
      false
    end

    def rule_question(comment)
      return "Sure." if comment =~ /.*\?\z/
      false
    end

    def rule_1_silence(comment)
      return "Fine. Be that way!" if (comment.nil? || comment.empty?)
      false
    end

end
