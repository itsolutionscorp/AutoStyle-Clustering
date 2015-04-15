class Prime

	def self.nth(number)
    target  ||= 1
    results ||= []
    while number > results.length
      truth_array = []
      target.downto(1).each do |integer|
        if target % integer == 0 
          truth_array << "not_prime" if integer != (target && 1)
          puts integer
        end
      end
      results << target unless truth_array.include?("not_prime")
      target += 1
    end
  end
end

Prime.nth(2)
