require 'prime'

def nth(nth_prime)
	Prime.first(nth_prime)[-1]
end
