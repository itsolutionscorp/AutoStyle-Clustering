# Rule template
#
# A subclass should implement:
# * applies?(message)
# * to_value(message)
class Rule
  # Defines a subclass and it's applies? method
  #
  # Example:
  #   Rule.define('Default')  {|_| true }
  #
  #   Rule::Default.new.applies?(nil)
  #   # => true
  def self.define(class_name, &applies)
    rule_class = Class.new(self)
    self.const_set(class_name, rule_class)
    rule_class.send :define_method, :applies?, &applies
  end

  # @param [String] response
  def initialize(response)
    @response = response
  end

  # @param [String] message to apply rules to
  # @return [String] if a rule was applied
  # @return [nil] if no rules could be matched
  def apply(message)
    @response if applies?(message)
  end

  # @abstract
  def applies?(message)
    raise NotImplementedError
  end
end

Rule.define('Empty')    {|message| message.to_s.empty? }
Rule.define('Shouting') {|message| message.upcase == message }
Rule.define('Question') {|message| message.end_with?("?") }
Rule.define('Default')  {|_| true }

class Bob
  DEFAULT_RULES = [
    Rule::Default.new("Whatever."),
    Rule::Question.new("Sure."),
    Rule::Shouting.new("Woah, chill out!"),
    Rule::Empty.new("Fine. Be that way!")
  ]

  # @param [Array<Rule>] rules
  def initialize(rules = DEFAULT_RULES)
    @rules = rules
  end

  # @param [String] message
  # @return [String] The response
  def hey(message)
    @rules.reverse_each do |rule|
      value = rule.apply(message)
      break value if value
    end
  end
end
