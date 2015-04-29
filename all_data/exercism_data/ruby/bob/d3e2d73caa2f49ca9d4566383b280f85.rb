########################################################
# Solution #4,
#   It would be interesting to define a series of blocks
#   for the conditions, and to use the result of evaluating
#   the blocks to determine the answer.  Alas, I have to go
#   to work.
########################################################

########################################################
# Solution #3, separate arrangement from work using
#              Answers as registered handlers.
#              This disperses responsibility for knowing
#              the 'right' answer into the answers themselves,
#              and suggests that an external configuration
#              for HANDLERS might be useful.
########################################################
class AnswerSilence

  def self.handles?(input)
    input.empty?
  end

  def reply
    'Fine. Be that way.'
  end
end

class AnswerQuestion

  def self.handles?(input)
    input.include?('?')
  end

  def reply
    'Sure.'
  end
end

class AnswerShout

  def self.handles?(input)
    input == input.upcase
  end

  def reply
    'Woah, chill out!'
  end
end

class AnswerDefault

  def self.handles?(input)
    true
  end

  def reply
    'Whatever.'
  end
end

class Bob
  HANDLERS = [AnswerSilence, AnswerQuestion, AnswerShout, AnswerDefault]

  def hey(input)
    answerer(input).reply
  end

  def answerer(input)
    HANDLERS.find {|answer| answer.handles?(input)}.new
  end

end

#
########################################################
# Solution #2, separate arrangement from work while
#              leaving case statement in factory.
#              This is a good intermediate choice that
#              simplifies Bob and the Answers and moves
#              the mess to the Factory.
########################################################
# class AnswerSilence
#   def reply
#     'Fine. Be that way.'
#   end
# end
# 
# class AnswerQuestion
#   def reply
#     'Sure.'
#   end
# end
# class AnswerShout
#   def reply
#     'Woah, chill out!'
#   end
# end
# 
# class AnswerDefault
#   def reply
#     'Whatever.'
#   end
# end
# 
# class AnswerFactory
#   def self.answer(input)
#     case 
#     when input == ''
#       AnswerSilence.new
#     when input == input.upcase
#       AnswerShout.new
#     when input.include?('?')
#       AnswerQuestion.new
#     else
#       AnswerDefault.new
#     end
#   end
# end
# 
# 
# class Bob
#   def hey(input)
#     AnswerFactory.answer(input).reply
#   end
# end
# 
########################################################
# Solution #1, first choice if nothing ever changes
########################################################
# class Bob
#
#   def hey(input)
#     case
#     when input == ''
#       'Fine. Be that way.'
#     when input == input.upcase
#       'Woah, chill out!'
#     when input.include?('?')
#       'Sure.'
#     else
#       'Whatever.'
#     end
#   end
#
# end
