class Beer

  attr_reader :containers, :pronouns
  attr_accessor :beverage

  def initialize
    self.containers = %w{bottles bottle}
    self.pronouns = %w{one it}
    self.beverage = "beer"
  end

  def sing(first, last=0)
    first.downto(last).collect { |num| verse(num) }.join("\n") + "\n"
  end

  def verse(num)
    num = Counter.new(num)
    [remaining(num), action(num), ""].join("\n")
  end

  def self.song(first=99)
    new.sing(first)
  end

  # @array container_list - [plural version, singular version]
  def containers=(container_list)
    @containers = HashWithIntegerAccess.new(container_list[0])
    @containers[1] = container_list[1]
  end

  private
  # @array pronoun_list - [generic version, specific version]
  def pronouns=(pronoun_list)
    @pronouns = HashWithIntegerAccess.new(pronoun_list[0])
    @pronouns[1] = pronoun_list[1]
  end

  def remaining(num)
    "#{num.to_s.capitalize} #{containers[num]} of #{beverage} on the wall, #{num} #{containers[num]} of #{beverage}."
  end

  def action(num)
    if num == 0
      return "Go to the store and buy some more, 99 #{containers[99]} of #{beverage} on the wall."
    end

    "Take #{pronouns[num]} down and pass it around, #{num-1} #{containers[num-1]} of #{beverage} on the wall."
  end

  class Counter
    attr_accessor :i

    def initialize(i)
      self.i = i
    end

    def -(other)
      Counter.new(i - other)
    end

    def to_i
      i
    end

    def to_s
      return "no more" if i == 0
      i.to_s
    end

    def ==(other)
      i == other
    end
  end

  class HashWithIntegerAccess < Hash
    def [](num)
      super(num.to_i)
    end

    def []=(num, value)
      super(num.to_i, value)
    end
  end
end

class Fixnum
  def method_missing(name, *args, &blk)
    if name.to_s.end_with? "_of"
      container = name[0..-4]
      beer = Beer.new
      beer.beverage = args.first
      beer.containers = [container, container[0..-2]]
      beer.sing(self)
    else
      super(name, *args, &blk)
    end
  end
end
