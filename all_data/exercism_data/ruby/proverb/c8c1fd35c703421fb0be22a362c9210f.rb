class Proverb

  def initialize(*args)
    @args = args
    @qualifier_boolean = @args.last.class == Hash
  end

  def to_s
    final_output = ""
    if @qualifier_boolean
      repeats = @args.length - 2
    else
      repeats = @args.length - 1
    end
    repeats.times do |i|
      final_output << "For want of a #{@args[i]} the #{@args[i+1]} was lost.\n"
    end
    if @qualifier_boolean
      final_output << "And all for the want of a #{@args.last[:qualifier]} nail."
    else
      final_output << "And all for the want of a #{@args[0]}."
    end
    final_output
  end

end
