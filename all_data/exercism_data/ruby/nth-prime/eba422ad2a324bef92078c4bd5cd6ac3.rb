require 'prime'

Prime.define_singleton_method(:nth) do |n|
  raise ArgumentError if n < 1
  Prime.first(n).last
end
