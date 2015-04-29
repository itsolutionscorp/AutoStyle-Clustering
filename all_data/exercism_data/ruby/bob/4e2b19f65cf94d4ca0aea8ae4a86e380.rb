class Bob
  def initialize
  end

  def hey(something)
    # make  nil case not an option
    something ||= ''
    case 
      when something.reverse[0] == "?"
        # this is a question!
        "Sure."
      when something.reverse[0] == "."
        # this is a statement!
        "Whatever."
      when something.empty?
        # saying nothing to him
        "Fine. Be that way."
      when something.upcase == something
        # shouting
        "Woah, chill out!"
      end
  end
end
