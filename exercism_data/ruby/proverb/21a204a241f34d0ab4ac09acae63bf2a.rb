class Proverb
  @saying

  def initialize(*lost_things, qualifier: "")
    @saying = ""

    lost_things.each_cons(2) do |want, lost|
      @saying << "For want of a #{want} the #{lost} was lost.\n"
    end
    qualifier << " " unless qualifier.empty?
    @saying << "And all for the want of a #{qualifier}#{lost_things.first}."
  end

  def to_s
    @saying
  end
end
