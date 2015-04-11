class Bob

  # Public API, method under test.
  def hey( input )
    interaction = interaction_for( clean( input ) )
    interaction.response
  end

  # Internal - return the interaction class for the given input
  def interaction_for( input )
    interactions.find{ |k| k.handles?( input ) }
  end


  # Internal - The list of responder classes that have a criteria
  def normal_interactions
    [ Question, Fine, Woah ]
  end

  # Internal The class that holds the default interaction
  def default_interaction
    Whatever
  end

  # Internal - all the interactions, making sure that the last interaction is 
  # the default
  def interactions 
    normal_interactions << default_interaction
  end

  # pre processing the input for all interactions
  def clean( input )
   input.gsub( %r/\s+/, '' )
  end
end

# Parent class of all Interactions. It defines Interaction DSL that child
# classes use to configure themselves.
#
# Each child class MUST use `matches` and `response` to define under what
# conditions it will match an input, and what it will respond with.
class Interaction
  def self.matches( *m )
    return @matches if m.empty?
    @matches = m.first
  end

  def self.response( *r )
    return @response if r.empty?
    @response = r.first
  end

  def self.handles?( input )
    input =~ matches
  end
end

# Default Interaction
class Whatever < Interaction
  matches  %r/.*/
  response "Whatever."
end

# Matches input that has no lower case characters
class Woah < Interaction
  matches   %r/\A[^a-z]+\Z/
  response "Woah, chill out!"
end

# Matches empty input
class Fine < Interaction
  matches  %r/\A\Z/
  response "Fine. Be that way."
end

# Matches input that ends in a question mark (?)
class Question < Interaction
  matches  %r/\?\Z/i
  response "Sure."
end
