#
# Skeleton file for the Python "Bob" exercise.
#

class ValidatorUtility:
	@staticmethod
	def isEmptyString (stringToValidate):

		if stringToValidate:
			if not stringToValidate.strip():
				return True
			else:
				return False

		return True
	@staticmethod
	def isUpperCase (stringToValidate):
		return stringToValidate.isupper()

	@staticmethod
	def isQuestion (stringToValidate):
		return stringToValidate.strip().endswith('?')


def hey(what):

	validator = ValidatorUtility()

	if validator.isEmptyString(what):
		return 'Fine. Be that way!'
	else:
		if validator.isUpperCase(what):
			return 'Whoa, chill out!'
		else:
			if validator.isQuestion(what):
				return 'Sure.'
	return 'Whatever.'
