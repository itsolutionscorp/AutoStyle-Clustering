class Proverb
  def initialize(*args)
    @args = args.last.is_a?(Hash) ? args[0..-2] : args
    @qualifier = args.last[:qualifier] + " " if args.last.is_a?(Hash)
  end

  def to_s
    @args.map.with_index do |arg, i|
      if i == @args.length - 1
        "And all for the want of a #{@qualifier}#{@args[0]}."
      else
        "For want of a #{arg} the #{@args[i + 1]} was lost."
      end
    end.join("\n")
  end
end
