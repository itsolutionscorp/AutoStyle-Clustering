module Raindrops

	require 'prime'

	SPECIAL_VALUES = {
		3 => "Pling",
		5 => "Plang",
		7 => "Plong"
	}.freeze

	def self.convert drop
		primes = prime_factors_for(drop)
		sounds  = ""
		SPECIAL_VALUES.each do |prime, sound|
			sounds << sound if primes.include? prime
		end
		sounds.empty? ? drop.to_s : sounds
	end

private

	def self.prime_factors_for(number)
		number.prime_division.map { |p| p[0] }
	end

end
