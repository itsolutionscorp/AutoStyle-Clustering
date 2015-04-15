class Proverb
  def initialize(*args, qualifier: nil)
    ## this is some ugly code, but it's working
    ## working around tests calling .to_s (unsure of best approach)
    @output = ''
    first_object = args[0]
    args.each_cons(2) do |array|
      @output << for_want(array[0], array[1])
    end
   @output << last_line(qualifier, first_object)
   @output.to_s
  end

  def for_want(object, second_object)
   "For want of a #{object} the #{second_object} was lost.\n"
  end

  def last_line(qualifier, first_object)
    ## was unsure of the best way to do spacing with the qualifier
    ## currently appends space if not nil
    qualifier += ' ' if qualifier
    "And all for the want of a #{qualifier}#{first_object}."
  end

  def to_s
    ## unsure why tests were calling proverb.to_s, just overrode it here
    @output
  end
end
