class Proverb
  def initialize(*objects, qualifier: nil)
    @objects = objects
    @qualifier = qualifier
  end

  def to_s
    sequence = @objects[1..-1].zip(@objects).inject("") do |string, (second, first)|
      string + "For want of a #{first} the #{second} was lost.\n"
    end
    sequence + "And all for the want of a #{qualifier}#{@objects[0]}."
  end

private

  def qualifier
    @qualifier ? @qualifier + " " : ""
  end

end
