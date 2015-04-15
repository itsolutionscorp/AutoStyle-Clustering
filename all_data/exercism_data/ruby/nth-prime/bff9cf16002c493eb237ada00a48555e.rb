require 'prime'

class << Prime
  def nth(n)
    fail(ArgumentError) if n < 1

    Prime.detect.with_index { |_p, i| (i + 1) == n }
  end
end
