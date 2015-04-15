module MoodDefinitions
  LACKADAISICAL = {
    silent: "Fine. Be that way!",
    loud: "Woah, chill out!" ,
    question: "Sure.",
    default: "Whatever."
  }
end

class Mood
  UnknownMoodError = Class.new(StandardError)

  def initialize(mood_type)
    @mood_type = mood_type
  end

  def typical_replies
    case @mood_type
    when :lackadaisical
      MoodDefinitions::LACKADAISICAL
    else
      raise UnknownMoodError
    end
  end
end

class Person
  MissingMoodError = Class.new(StandardError)
  MissingBrainTaskError = Class.new(StandardError)

  def hey(sentence)
    @brain_task = BrainTask.new(self, sentence)
    move_lips
  end

  def typical_replies
    raise MissingMoodError unless @mood
    @mood.typical_replies
  end

  private

  def move_lips
    raise MissingBrainTaskError unless @brain_task
    @brain_task.compute_conversation
  end
end

class BrainTask
  attr_reader :owner

  def initialize(owner, task)
    @owner = owner
    @task = task
  end

  def compute_conversation
    something_said = sanitize_input(@task)
    interpreter = ConversationInterpreter.new(self, something_said)
    interpreter.compute_appropriate_reply
  end

  private

  def sanitize_input(string_or_nil)
    string_or_nil.to_s.strip
  end
end

class ConversationInterpreter
  def initialize(brain, conversation)
    @brain = brain
    @conversation = conversation
  end

  def compute_appropriate_reply
    case
    when silent?
      @brain.owner.typical_replies[:silent]
    when loud?
      @brain.owner.typical_replies[:loud]
    when question?
      @brain.owner.typical_replies[:question]
    else
      @brain.owner.typical_replies[:default]
    end
  end

  private

  def loud?
    @conversation == @conversation.upcase
  end

  def question?
    @conversation.end_with?("?")
  end

  def silent?
    @conversation.empty?
  end
end

class Bob < Person
  def initialize
    @mood = Mood.new(:lackadaisical)
  end
end
