class Rule
  attr_reader :respond 
  
  # @param [Hash] options 
  # @option options [lambda] :when The rule's condition
  # @option options [String] :then The response when the rule is applied
  def initialize(options)
    @applies = options.fetch(:when)
    @respond = options.fetch(:then)
  end
  
  def applicable?(message)
    @applies.(message)
  end
end

class Bob
  DEFAULT_RULES = [
    Rule.new(when: ->(message) { message.to_s.empty? }, 
             then: "Fine. Be that way!"),
    Rule.new(when: ->(message) { message.upcase == message },
             then: "Woah, chill out!"),
    Rule.new(when: ->(message) { message.end_with?("?") },
             then:  "Sure."),
    Rule.new(when: ->(_) { true },
             then:  "Whatever.")
  ]

  # @param [Array<Rule>] rules
  def initialize(rules = DEFAULT_RULES)
    @rules = rules
  end

  # @param [String] message
  # @return [String] The response
  def hey(message)
    @rules.find{|rule| rule.applicable?(message) }.respond
  end
end
