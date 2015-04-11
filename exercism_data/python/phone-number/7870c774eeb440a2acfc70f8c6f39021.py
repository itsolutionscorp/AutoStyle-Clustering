import re
'''
	This class represents a phone, that has an associated phone number
'''
class Phone(object):
	'''
		The init function that initializes the phone number with a processed version of the
		provided phone number
		Also sets a default number to 10 0's
	'''
	def __init__(self, phone_num):
		self.default_num = '0000000000'
		self.number = self.process_num(phone_num)
		self.areaCode = self.number[0:3]

	'''
		Given a raw string that represents a phone number,
		this function strips any non alpha-numeric characters off
		It also checks if a phone number is valid in terms of:
		-> length & international code
	'''
	def process_num(self, phone_num):
		phone_num = re.sub('[\s\W]', '', phone_num)
		num_digits = len(phone_num)

		# Normal length for a phone number
		if num_digits == 10:
			return phone_num
		# If length exceeds by one digit, check if leading digit is the US code
		elif num_digits == 11 and phone_num[0] == '1':
			return phone_num[1:]
		# Otherwise return the default phone number for invalid numbers
		else:
			return self.default_num

	'''
		This function returns the area code of an instance
	'''
	def area_code(self):
		return self.areaCode

	'''
		This function returns a neatly formatted string containing the phone
	'''
	def pretty(self):
		pretty_num = "({0}) {1}-{2}".format(self.areaCode, self.number[3:6],self.number[6:])
		return pretty_num
