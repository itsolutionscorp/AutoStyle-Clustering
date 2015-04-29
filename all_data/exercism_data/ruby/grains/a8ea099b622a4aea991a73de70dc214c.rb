module GrainNaive
  def initialize
    @@s = {}
  end

  def square n
    @@s[n] ||= 2 ** (n-1)
  end

  def total(n: 64)
    (1..n).reduce(0) do |sum,i|
      sum + square(i)
    end
  end
end

module GrainTailRec
  # SLOW!
  def initialize
    RubyVM::InstructionSequence.compile_option = {
      :tailcall_optimization => true,
      :trace_instruction => false
    }
  end

  def square(n, sum: 1)
    if n == 1
      sum
    else
      square(n-1, sum: sum * 2)
    end
  end

  def total(i: 64, sum: 0)
    if i == 0
      sum
    else
      total(i: i - 1, sum: sum + square(i))
    end
  end
end

class Grains
  include GrainTailRec
end
