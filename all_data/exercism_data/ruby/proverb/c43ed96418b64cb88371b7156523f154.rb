class Proverb

  attr_reader :args,
              :qualifier

  def initialize(*args)
    has_qualifier = args.last.is_a?(Hash)
    @args = has_qualifier ? args[0..-2] : args
    @qualifier = has_qualifier ? args.pop[:qualifier] : ""
  end

  def to_s
    song = []
    args.each_with_index.map do |_, i|
      song << phrase(args[i], args[i+1]) unless i == args.length - 1
    end
    song << "And all for the want of a #{qualifier != "" ? qualifier + " " : qualifier}#{args[0]}."
    song = song.join("\n")
  end

  def phrase(first, second)
    "For want of a #{first} the #{second} was lost."
  end

end
