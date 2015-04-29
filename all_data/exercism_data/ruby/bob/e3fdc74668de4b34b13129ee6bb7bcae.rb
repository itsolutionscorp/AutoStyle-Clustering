# Rule template
#
# A subclass should implement:
# * applies?(message)
# * to_value(message)
class Rule
  # @param [String] response
  def initialize(response, &applies)
    @response = response
    @applies = applies
  end

  # @param [String] message to apply rules to
  # @return [String] if a rule was applied
  # @return [nil] if no rules could be matched
  def apply(message)
    @response if @applies.(message)
  end
end

class Bob
  DEFAULT_RULES = [
    Rule.new("Whatever.") {|_| true },
    Rule.new("Sure.") {|message| message.end_with?("?") },
    Rule.new("Woah, chill out!") {|message| message.upcase == message },
    Rule.new("Fine. Be that way!") {|message| message.to_s.empty? }
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
