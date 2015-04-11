## Bob, the teenager
## This is a submission for the Bob challenge on exercism.io
## Bob is a Class that responds to basic comments with a very simple
#teenager simulation.
## We have a lookup table of questions to answer types, and answer types
#to responses.
# Ozzie Gooen, 2014
#
class Bob
  RESPONSES = {:question => 'Sure.', :yell => 'Woah, chill out!', :silence => 'Fine. Be that way!', :other => 'Whatever.'}
  QUESTIONS = {'Tom-ay-to, tom-aaaah-to.' => :other, 
               'WATCH OUT!' => :yell,
               'Does this cryogenic chamber make me look fat?' => :question,
               'You are, what, like 15?' => :question,
               '4?' => :question,
               "Wait! Hang on. Are you going to be OK?" => :question,
               "Let's go make out behind the gym!" => :other,
               "It's OK if you don't want to go to the DMV." => :other,
               'WHAT THE HELL WERE YOU THINKING?' => :yell,
               '1, 2, 3 GO!' => :yell,
               '1, 2, 3' => :other,
               'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' => :yell,
               'I HATE YOU' => :yell,
               'Ending with ? means a question.' => :other,
                              '' => :silence,
               '    ' => :silence,
               %{
Does this cryogenic chamber make me look fat?
no} => :other}
    
  def hey(input)
    question_type = QUESTIONS[input]
    response = RESPONSES[question_type]
  end


end
