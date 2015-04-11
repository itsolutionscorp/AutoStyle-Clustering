class PrimeFactors
  def self.for(n)
    factors = []
    start = 2
    until n == 1
      factor = (start..n ** 0.5).find {|f| (n % f).zero?} || n
      start = factor
      n /= factor
      factors << factor
    end
    factors
  end
end

RubyVM::InstructionSequence.compile_option = {
  :tailcall_optimization => true,
  :trace_instruction => false
}

RubyVM::InstructionSequence.new(<<-EOF).eval
  class PrimeFactors
    def self.for_tco(n, factors = [], start = 2)
      return factors if n == 1
      factor = (start..n ** 0.5).find {|f| (n % f).zero?} || n
      for_tco(n / factor, factors << factor, factor)
    end
  end
EOF
