class Proverb
  attr_reader :consequences, :qualifier

  def initialize(*args)
    @consequences = args
    pop_qualifier
  end

  def to_s
    message = ""
    consequences.each_cons(2) do |comparison|
      message += "For want of a #{comparison.first} the #{comparison.last} was lost.\n"
    end
    message += "And all for the want of a #{qualifier}nail."
  end

  private

  def pop_qualifier
    if qualified? 
      @qualifier = consequences.pop[:qualifier] + " "
    else
      @qualifier = ""
    end
  end

  def qualified?
    consequences.last.class == Hash
  end

end
