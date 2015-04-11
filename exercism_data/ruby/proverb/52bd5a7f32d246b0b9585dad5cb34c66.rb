class Proverb
  def initialize(*args)
    @proverb = []

    args.each do |arg|
      if arg.is_a?(String)
        @proverb << arg
      else
        @qualifier = arg[:qualifier]
      end
    end
  end

  def for_want(first, second)
    "For want of a #{first} the #{second} was lost.\n"
  end

  def to_s
    number = @proverb.count - 1
    if @qualifier 
      @qualifier = "#{@qualifier} nail"
    end
    string = ''
    number.times do |x|
      string += for_want(@proverb[x], @proverb[x + 1])
    end
    string += "And all for the want of a #{@qualifier || @proverb.first}."
    string
  end
end
