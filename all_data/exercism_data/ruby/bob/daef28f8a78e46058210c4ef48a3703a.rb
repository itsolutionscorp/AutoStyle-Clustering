class Bob
  # Given a string input, Bob flips through his list of irritating responses
  # and picks the one he feels most strongly about.
  def hey(input)
    selected_strategy = select_strategy(input)
    execute(selected_strategy, input: input)
  end

  private
  # Find the first strategy that offers a response to the given input
  def select_strategy(input)
    strategies.values.find { |s| s.(input.to_s) }
  end

  # Obscures the awkwardness of finding a strategy lambda and then re-running it
  def execute(strategy, input: nil)
    strategy.(input)
  end

  # Given a string, these strategy lambdas may choose to return a string 
  # response. Try them out in order, i.e. with Enumerable#first.
  def strategies
    {
      no_letters: ->(i) { 'Fine. Be that way.' unless (i =~ /[a-z]/i) },
      all_caps:   ->(i) { 'Woah, chill out!'   if (i.upcase == i)     },
      questions:  ->(i) { 'Sure.'              if i.end_with?('?')    },
      default:    ->(i) { 'Whatever.'                                 }
    }
  end
end
