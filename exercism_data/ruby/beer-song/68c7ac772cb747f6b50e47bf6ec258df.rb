class BeerSong
	def verse(num)
		number = ->(n) {(n == 0) ? 'no more' : n}
		next_number = ->(n) {(n == 0) ? 99 : n - 1}
		bottles = ->(n) {'bottle' + ((n == 1) ? '' : 's')}
		one_or_it = ->(n) {(n == 1) ? 'it' : 'one'}

		[
			[
				"#{number.call(num)} #{bottles.call(num)} of beer on the wall".capitalize,
				"#{number.call(num)} #{bottles.call(num)} of beer.",
			].join(', '),
			[
				(num > 0) ? "Take #{one_or_it.call(num)} down and pass it around" : 'Go to the store and buy some more',
				"#{number.call(next_number.call(num))} #{bottles.call(next_number.call(num))} of beer on the wall.",
			].join(', '),
			''
		].join("\n")
	end

	def verses(start, stop)
		start.downto(stop).collect{|i| verse(i)}.join("\n") + "\n"
	end

	def sing
		verses(99, 0)
	end
end
