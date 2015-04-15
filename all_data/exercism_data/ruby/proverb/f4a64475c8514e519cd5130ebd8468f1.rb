class Proverb
  def initialize(*objects, qualifier: nil)
    @objects = objects
    @qualifier = qualifier
  end

  def to_s
    consequences + ending
  end

private

  def consequences
    object_pairs.map { |first, second| "For want of a #{first} the #{second} was lost.\n" }.join
  end

  def ending
    "And all for the want of a #{qualifier}#{@objects[0]}."
  end

  def object_pairs
    @objects.zip(@objects[1..-1])[0..-2]
  end

  def qualifier
    @qualifier ? "#{@qualifier} " : ""
  end
  
end
