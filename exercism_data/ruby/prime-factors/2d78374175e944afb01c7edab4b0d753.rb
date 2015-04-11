require 'prime'
# class PrimeFactors


# SORRY this soesnt work as expected. please dont review it


class PrimeFactors
  def self.for(n)
    op = []
    Prime.take_while {|p|  p <= Math.sqrt(n) }.each{|e| (op << e; op << PrimeFactors.for(n/e)) if n%e == 0}
    op.compact.flatten
  end

#   def self.for(n)
#     n1 = n
#     op = []
#     while n.even?
#       op << 2
#       n = n / 2
#     end
#     puts "HERE: #{n1}"
#       n = n1
#     if Math.sqrt(n) >= 3
#       i = 3
#       until i < Math.sqrt(n)
#         while n % i == 0
#           op << i
#           n = n / i
#         end
#         i += 2
#       end
#       op << n if n > 2
#     end
#     op
#   end
end
