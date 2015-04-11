class WordProblem
  OPS = {
    "plus" => "+",
    "minus" => "-",
    "divided by" => "/",
    "multiplied by" => "*"
  }

  def initialize(s)
    @s = s.gsub("What is ", "").chomp("?")
    OPS.each { |k, v| @s.gsub!(k, v) }
    raise ArgumentError unless @s =~ /^[\d+\-*\/ ]+$/
  end

  def answer
    res = 0
    ops = @s.scan(/ ([+\-*\/]) /).flatten
    nums = @s.scan(/-?\d+/).map(&:to_i)
    ops.each_with_index do |op, i|
      res = nums[i] if i == 0
      res = res.method(op.to_sym).call(nums[i + 1])
    end
    res
  end
end
