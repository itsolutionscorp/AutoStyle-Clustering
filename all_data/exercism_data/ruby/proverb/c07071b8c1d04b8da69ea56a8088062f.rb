class Proverb
  attr_reader :wants, :options

  def initialize(*args)
    @options = args.last.is_a?(Hash) ? args.pop : {}
    @wants = args
  end

  def to_s
    (wants_list + [initial_want]).join("\n")
  end

  def initial_want
    initial = wants.first
    initial.prepend("#{options[:qualifier]} ") if options[:qualifier]
    "And all for the want of a #{initial}."
  end

  def wants_list
    wants_couples.map do |first, second|
      "For want of a #{first} the #{second} was lost."
    end
  end

  def wants_couples
    wants.each_cons(2).to_a
  end
end
