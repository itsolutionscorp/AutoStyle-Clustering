class Bob
  def initialize
  end

  def hey(something)
    # make  nil case not an option
    something ||= ''
    case 
      when something.empty?
        # saying nothing to him
        "Fine. Be that way."
      when something.upcase == something
        # shouting
        "Woah, chill out!"
      when something.end_with?("?")
        # this is a question!
        "Sure."
      when something.end_with?("." , "!")
        # this is a statement!
        "Whatever."
      end
  end
end
