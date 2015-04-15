class TriangleError < StandardError; end
class Triangle
	attr_reader :sides

	def initialize(a,b,c)
		@sides = [a,b,c]
		raise TriangleError if all_sides_positive? || invalid_sides?
	end


	def kind
		{1 => :equilateral, 2 => :isosceles, 3 => :scalene}[sides.uniq.length]
	end

	private 

	def all_sides_positive?
		sides.any? { |side| side <= 0 }
	end


	def invalid_sides?
		max = sides.max
		other_sides = sides.sort[0..1]
		max >= other_sides.reduce(&:+)
	end

end





	# def initialize(a,b,c)
	# 	@a = a
	# 	@b = b
	# 	@c = c
	# 	#if something bad happens then raise an alarm
	# 	raise TriangleError.new if (@a <= 0) || (@b <= 0) || (@c <= 0)
	# 	raise TriangleError if (@a == @b && @a+@b <= @c) || (@a == @c && @a+@c <= @b) || (@b == @c && @b+@c <= @a) || (@b+@c <= @a) || (@b+@a <= @c) || (@a+@b <= @c)
	# end

	# def kind
	# 	if @a == @b && @a == @c
	# 		:equilateral
	# 	elsif (@a == @b && @a != @c) || (@a == @c && @a != @b) || (@c == @b && @c != @a)
	# 		:isosceles
	# 	else
	# 		:scalene
	# 	end
	# end


	# def barfoo?
	# 	unique_sides = sides.uniq
	# 	if unique_sides.length == 2
	# 		a, b, c = sides.sort
	# 		a + b <= c
	# 	end
	# 	(@a == @b && @a+@b <= @c) || (@a == @c && @a+@c <= @b) || (@b == @c && @b+@c <= @a) 
	# end

	# def foobar?
	# 	a, b, c = sides.sort
	# 	(b+c <= a) || (b+a <= c) || (a+b <= c)
	# end

#if a is equal to b and c is greater than the two combined raise error
#if b is equal to c and a is greater than the two combined raise error
#if a is equal to c and b is greater than the two combined raise error
