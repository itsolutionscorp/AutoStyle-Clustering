# A set of rules that are applied in order
# @see Rule
class RuleSet
  # @param [#apply] rules List of rules
  def initialize(*rules)
    @rules = Array(rules)
  end

  # @param [String] message to apply rules to
  # @return [String] if a rule was applied
  # @return [Rule::NotApplicable] if no rules could be matched
  def apply(message)
    value = Rule::NotApplicable
    
    @rules.each do |rule|
      value = rule.apply(message)
      break unless value == Rule::NotApplicable
    end
    
    value
  end
end

# Rule template
#
# A subclass should implement:
# * applies?(message)
# * to_value(message)
class Rule
  # Value for when a rule can't be applied
  class NotApplicable
    def to_s
      "Your message did not match any rules"
    end
  end

  # @param [String] message to apply rules to
  # @return [String] if a rule was applied
  # @return [Rule::NotApplicable] if no rules could be matched
  def apply(message, &block)
    if applies?(message)
      to_value(message)
    else
      NotApplicable
    end
  end

  private
  # @abstract 
  def applies?(message)
    raise NotImplementedError
  end

  # @abstract 
  def to_value(message)
    raise NotImplementedError
  end
end

# Applies if the message is empty
class EmptyRule < Rule
  private
  def applies?(message)
    message.to_s.empty?
  end

  def to_value(_)
    "Fine. Be that way!"
  end
end

# Applies when SHOUTING
class ShoutingRule < Rule
  private
  def applies?(message)
    message.upcase == message
  end

  def to_value(_)
    "Woah, chill out!"
  end
end

# Applies if the message is a question
class QuestionRule < Rule
  private
  def applies?(message)
    message.end_with?("?") 
  end

  def to_value(_)
    "Sure."
  end
end

# Always applicable
class DefaultRule < Rule
  private
  def applies?(_)
    true
  end

  def to_value(_)
    "Whatever."
  end
end

# a lackadaisical teenager
class Bob
  DEFAULT_RULES = RuleSet.new(EmptyRule.new,
                              ShoutingRule.new,
                              QuestionRule.new,
                              DefaultRule.new)

  # @param [#apply] rule_set
  def initialize(rule_set = DEFAULT_RULES)
    @rule_set = rule_set
  end

  # @param [String] message
  # @return [String] The response
  def hey(message)
    @rule_set.apply(message).to_s
  end
end
