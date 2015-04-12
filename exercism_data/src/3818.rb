def compute(x,y)
		x_array = x.split("")
		y_array = y.split("")

		if x_array == y_array
			0
		else x_array != y_array
			if x_array.length <= y_array.length
				x_array.zip(y_array).count { |a, b| a != b }
			else x_array.length > y_array.length
				y_array.zip(x_array).count { |a, b| a != b }
			end
		end
	end
end

=begin
______________________________________________
Explanation with example:
Hamming.compute('oneone','onetwo')
x = 'oneone', which becomes ['o','n','e','o','n','e']
y = 'onetwo', which becomes ['o','n','e','t','w','o']

Since the two arrays DO NOT EQUAL, and they have EQUAL LENGTH
 'x_array.zip(y_array).count { |a, b| a != b }' occurs:

['o','n','e','o','n','e'].zip(['o','n','e','t','w','o']) is
	[   ['o','o'],  ['n','n'],  ['e','e'],  ['o','t'],  ['n','w'],  ['e','o'],  ]
	When calling '.count' here, there are 6.
Since we are further specifying {|a, b| a!=b} we are counting when ['a','b'] are NOT equal.
    This gives us 3.
______________________________________________

I've run this in the console and all the examples give me good results,
  but when I run hamming_test.rb I get:

------
# Running:

.SSSSSSS

Finished in 0.001559s, 5131.4945 runs/s, 641.4368 assertions/s.

8 runs, 1 assertions, 0 failures, 0 errors, 7 skips
------
=