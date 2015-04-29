class Bob:
	def hey(self, input):
		input = input.strip()
		responses = {
			"no_input": "Fine. Be that way!",
			"uppercase": "Woah, chill out!",
			"question": "Sure.",
			"default": "Whatever."
		}

		response = responses["default"]

		if not input:
			response = responses["no_input"]
		elif input.isupper():
			response = responses["uppercase"]
		elif input.endswith("?"):
			response = responses["question"]

		return response
