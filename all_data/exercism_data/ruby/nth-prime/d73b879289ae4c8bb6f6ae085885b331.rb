require 'prime'
class Prime
    def nth n
      raise ArgumentError if n==0
      Prime.take(n)[-1]
    end
end
