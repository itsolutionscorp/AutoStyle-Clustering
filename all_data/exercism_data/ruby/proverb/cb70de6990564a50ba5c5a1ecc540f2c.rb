class Proverb
  @saying

  def initialize(*lost_things, qualifier: "")
    @saying = ""

    lost_things.each_cons(2) do |things|
      @saying << "For want of a #{things[0]} the #{things[1]} was lost.\n"
    end
    qualifier << " " unless qualifier.empty?
    @saying << "And all for the want of a #{qualifier}#{lost_things[0]}."
  end

  def to_s
    @saying
  end
end
