class Proverb
  def initialize(*args, qualifier: nil)
    @args = args
    @qualifier = qualifier
  end

  attr_reader :args, :qualifier

  def qualifier
    @qualifier ? "#{@qualifier} " : ""
  end

  def ending
    qualifier + @args[0]
  end

  def to_s
    ans = ''
    args.each.with_index(1) do |item, i|
      if i >= args.length
        break
      else
        ans += "For want of a #{args[i-1]} the #{args[i]} was lost.\n"
      end
    end
    ans += "And all for the want of a #{ending}."
  end
end
