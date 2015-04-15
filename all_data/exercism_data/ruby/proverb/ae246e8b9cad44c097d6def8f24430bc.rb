require 'pry'
class Proverb
  attr_accessor :output

  def initialize(*args)
    @output = ""

    if args.last.class == Hash
      first_thing = "#{args.last[:qualifier]} #{args.first}"
      args.pop
    else
      first_thing = args.first
    end

    args[0...-1].each_index do |index|
      thing      = args[index]
      next_thing = args[index + 1]
      @output.concat "For want of a #{thing} the #{next_thing} was lost.\n"
    end

    @output.concat "And all for the want of a #{first_thing}."
  end

  def to_s
    @output
  end
end
