require 'highline/import'

def test
	questions = ['How are you today?', 'How were you yesterday?', 'How will you be tomorrow?', 'How did you feel on the day you were born?', 'How will you feel on the day that you die?', 'How much wood could a wood chuck chuck if a wood chuck could chuck wood? Please be specific.', 'What is my middle name?', 'What is your middle name?', 'Coke or Pepsi?', 'Do you like Jack Daniels?']

	while true
		question = questions[rand(10)]
		if question == questions[9]
			answer = ask(question).downcase
			if answer == 'yes'
				say('That is the right answer')
				return
			else
				say('That is the wrong answer. You DO like Jack Daniels')
			end
		end
		do_not_care = ask(question)
	end
end

test
