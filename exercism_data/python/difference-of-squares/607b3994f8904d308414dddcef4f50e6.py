#using **2 over math.sqrt() so i can get int's instead of floats.

def square_of_sum(user_input):
	
	#return sqare of sum of all whole numbers from 1 to user_input
	return sum(range(user_input+1))**2

def sum_of_squares(user_input):

	#calculate sum of squares for all whole numbers from 1 to user_input
	return sum(n**2 for n in range(user_input+1))

def difference(user_input):

	return square_of_sum(user_input) - sum_of_squares(user_input)
